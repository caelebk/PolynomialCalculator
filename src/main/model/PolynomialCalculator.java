package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

//Calculator for polynomials
public class PolynomialCalculator extends Calculator implements Saveable {

    public static final String x = "x";
    public static final char plus = '+';


    private TreeMap<Double, Double> poly;
    private String polyExp;
    private String polyExpWithSpace;

    //Constructor that makes empty polynomial
    public PolynomialCalculator() {
        poly = new TreeMap<Double, Double>(Collections.reverseOrder());
    }

    //Constructor to make testing much easier
    public PolynomialCalculator(TreeMap<Double, Double> x) {
        poly = x;
    }

    //Constructor for loaded string (ONLY USED BY READER)
    public PolynomialCalculator(String x) {
        poly = new TreeMap<Double, Double>(Collections.reverseOrder());
        polyExp = x;
        convertLoadedString();
    }

    //EFFECTS: adds a term to the polynomial
    public void addTerm(double deg, double val) {
        poly.put(deg, val);
    }

    //REQUIRE: valid polynomial inputted
    //EFFECTS: Turns inputted load to a polynomial
    private void convertLoadedString() {
        polyExpWithSpace = polyExp;
        polyExp = polyExp.replaceAll("\\s+","");
        String[] temp = polyExp.split("x|\\+");
        Stack<String> stack = new Stack<>();
        double val = 0;
        double deg = 0;
        for (int x = 0; x < temp.length; x++) {
            if (!temp[x].contains("^")) {
                val = Double.parseDouble(temp[x]);
            } else {
                deg = Double.parseDouble(temp[x].substring(1));
                addTerm(deg, val);
            }
            if (x == temp.length - 1 && !temp[x].contains("^")) {
                addTerm(0, val);
            }
        }

    }

    //REQUIRES: Polyexp to be a polynomial
    //EFFECTS: checks if polyexp is a valid polynomial
    public boolean valid() {
        String[] temp = polyExpWithSpace.split(" ");
        for (int x = 0; x < temp.length; x++) {
            String s = temp[x];
            if (x % 2 == 0) {
                if (!validTerm(s)) {
                    return false;
                }
            } else {
                if (!s.contains("+")) {
                    return false;
                }
            }
        }
        return true;
    }

    //EFFECTS: checks if the string is a valid term
    public boolean validTerm(String str) {
        if (!str.contains(x + "^")) {
            return isNumber(str);
        }
        String beforeX = str.substring(0,str.indexOf(x));
        String afterexp = str.substring(str.indexOf("^") + 1);

        return isNumber(afterexp) && isNumber(beforeX);
    }

    //MODIFIES: this
    //EFFECTS: Finds the derivative of the existing polynomial.
    //         -Given the existing polynomial (Treemap)
    //         -Loop through the keys of the treemap
    //         -Do derivative calculations
    //         -Add to new derivative treemap
    //         -Replace poly with new derivative treemap
    public void derivative() {
        TreeMap<Double, Double> der = new TreeMap<Double, Double>(Collections.reverseOrder());
        double tempExp;
        double tempCoef;
        if (poly.isEmpty()) {
            return;
        }
        for (double x : poly.keySet()) {
            if (x != 0) {
                tempExp = x - 1;
                tempCoef = x * poly.get(x);
                der.put(tempExp, tempCoef);
            }
        }
        poly = der;
    }

    //EFFECTS: Convert the term into a String
    //        -Given a degree and value
    //        -if the value is 0 return an empty string as term
    //        -if the degree is 0 just return the value
    //        -neither of the above, return the String with the following template "coefficient x^ degree"
    private String convertToTerm(double d, double v) {
        DecimalFormat df = new DecimalFormat("###.#");
        if (v == 0) {
            return "";
        } else if (d == 0) {
            return df.format(v);
        } else {
            return df.format(v) + x + "^" + df.format(d);
        }
    }

    //EFFECTS: Given the string, if there's an unnecessary + at the end, remove it.
    private String deletePlus(String polyStr) {
        if (polyStr.length() > 2 && polyStr.charAt(polyStr.length() - 2) == plus) {
            return polyStr.substring(0, polyStr.lastIndexOf(plus) - 1);
        }
        return polyStr;
    }

    //EFFECTS: Convert the existing treemap into a String representation of a polynomial
    public String getPolyExp() {
        String polyStr = "";
        Iterator<Double> iterator = poly.keySet().iterator();
        double degree;
        double value;
        while (iterator.hasNext()) {
            //loop through the set of keys (degrees)
            degree = iterator.next();
            value = poly.get(degree);
            //Obtain the key
            while (value == 0 && iterator.hasNext()) {
                //while the coefficient of current term is 0 and it's not the last term
                // skip until it finds a coefficient that is not 0 and not last term.
                degree = iterator.next();
                value = poly.get(degree);
            }
            if (!iterator.hasNext()) {
                //if the coefficient isn't 0 and it's the last term
                polyStr += convertToTerm(degree, value);
            } else {
                //if the coefficient isn't 0 and it's not the last term
                polyStr += convertToTerm(degree, value) + " " + plus + " ";
            }
        }
        return deletePlus(polyStr);
    }

    //REQUIRES: valid polynomial
    //EFFECTS: Evaluates the polynomial at a given point
    public double eval(double sol) {
        double sum = 0.0;
        for (double x : poly.keySet()) {
            if (x == 0) {
                sum = add(sum, poly.get(x));
            } else {
                sum = add(sum, multiply(poly.get(x), Math.pow(sol, x)));
            }
        }
        return sum;
    }

    //EFFECTS: Save the current expression.
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(getPolyExp());
        printWriter.print(Reader.DELIMITER);
        printWriter.println();
    }

}
