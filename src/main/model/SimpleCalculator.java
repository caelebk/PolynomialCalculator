package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.Stack;

//Calculator for simple mathemetical expressions
public class SimpleCalculator extends Calculator implements Saveable {

    public static final String operations = "-+*/";

    private String[] infix;
    private String expression;
    private String postfix;

    public SimpleCalculator(String str) {
        expression = str;
        infix = expression.split("\\s+");
        if (valid()) {
            infixToPostfixConversion();
        }
    }

    //REQUIRES: infixtopostfixconversion()
    //EFFECTS: evaluates the given statement, currently with orders of operation
    public double eval() {
        Stack<Double> stack = new Stack<>();
        for (int x = 0; x < postfix.length(); x++) {
            String num = "";
            if (numbers.contains(postfix.substring(x, x + 1))) {
                num += postfix.substring(x, x + 1);
                int y = x + 1;
                while (!postfix.substring(y, y + 1).equals(" ")) {
                    num += postfix.substring(y, y + 1);
                    x = y++;
                }
                stack.push(Double.parseDouble(num));
            } else if (!postfix.substring(x, x + 1).equals(" ")) {
                double n1 = stack.pop();
                double n2 = stack.pop();
                stack.push(chooseOperation(postfix.substring(x, x + 1), n2, n1));
            }
        }
        return stack.pop();
    }

    //EFFECTS: gives the mathematical expressions
    public String getExpression() {
        return expression;
    }

    //EFFECTS: returns the postfix expression.
    public String getPostfix() {
        return postfix;
    }

    //REQUIRES: Must be one of +/-* as op.
    //EFFECTS: Decide what operation to use
    private double chooseOperation(String op, double n1, double n2) {
        switch (op) {
            case "+":
                return add(n1, n2);
            case "-":
                return subtract(n1, n2);
            case "*":
                return multiply(n1, n2);
            default:
                return divide(n1, n2);
        }
    }

    //EFFECTS: If the String is a valid mathematical statement
    //         - return true
    //         - else return false
    public boolean valid() {

        for (int x = 0; x < infix.length; x++) {
            String temp = infix[x];
            if (x % 2 == 0) {
                for (int y = 0; y < temp.length(); y++) {
                    if (!numbers.contains(temp.substring(y,y + 1))) {
                        return false;
                    }
                }
            } else {
                if (!operations.contains(temp) || x == infix.length - 1) {
                    return false;
                }
            }
        }
        return true;
    }

    //EFFECTS: Save the current expression.
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(expression);
        printWriter.print(Reader.DELIMITER);
        printWriter.println();
    }


    //EFFECTS: Converts the infix expression to a postfix expression so pemdas exists
    public void infixToPostfixConversion() {
        Stack<String> stack = new Stack<String>();
        this.postfix = "";
        for (String s : infix) {
            if (isNumber(s)) {
                postfix += s + " ";
            } else {
                whileStackEmpty(stack, s);
                stack.push(s);
            }
        }
        whileStackEmpty(stack, "");
    }

    private void whileStackEmpty(Stack<String> st, String s) {
        while (!st.isEmpty() && precedence(s) <= precedence(st.peek())) {
            postfix += st.pop() + " ";
        }
    }

    //EFFECTS: Determines the precedences of the operator
    private int precedence(String c) {
        if (c.equals("+") || c.equals("-")) {
            return 1;
        } else if (c.equals("*") || c.equals("/")) {
            return 2;
        }
        return -1; // shouldn't return this
    }

}
