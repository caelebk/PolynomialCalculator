package model;

//Calculator abstract
public abstract class Calculator {

    public static final String numbers = "1234567890";

    //EFFECTS: add the two doubles
    public double add(double x, double y) {
        return x + y;
    }

    //EFFECTS: Subtract the first double by the second
    public double subtract(double x, double y) {
        return x - y;
    }

    //EFFECTS: multiply the two doubles
    public double divide(double x, double y) {
        return x / y;
    }

    //EFFECTS: Divide the first double by the second
    public double multiply(double x, double y) {
        return x * y;
    }

    //EFFECTS: Checks if the expression is valid.
    public abstract boolean valid();

    //REQUIRES: x not be an empty string
    //EFFECTS: Determine if x is a String consisting of only numbers
    public boolean isNumber(String x) {
        if (x.equals("")) {
            return true;
        } else if (numbers.contains(x.substring(0,1))) {
            return isNumber(x.substring(1));
        } else {
            return false;
        }
    }

}
