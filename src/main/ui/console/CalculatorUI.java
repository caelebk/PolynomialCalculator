package ui.console;

import java.util.Scanner;

public class CalculatorUI {

    public static final String op1 = "(Simple Calculations)";
    public SimpleCalculatorUI s1;
    public Scanner input;
    public String command;
    public int option;
    public boolean currentlyRunning;

    public CalculatorUI() {
        runCalculator();
    }

    private void runCalculator() {
        currentlyRunning = true;
        command = null;
        option = 0;
        input = new Scanner(System.in);

        while (currentlyRunning) {
            displayOptions();
            command = input.next();
            processOption(command);
        }

    }

    private void processOption(String in) {
        try {
            option = Integer.parseInt(in);
            System.out.println("----------------------------------------");
            System.out.print("Option " + option + " was selected: ");
            decidedOption(option);
        } catch (NumberFormatException e) {
            System.out.println("----------------------------------------");
            System.out.println("A number wasn't inputted; therefore, the calculator will end now.");
            currentlyRunning = false;
        } finally {
            System.out.println("Thank you for using our calculator!");

        }
    }

    private void decidedOption(int op) {
        if (op == 1) {
            System.out.println(op1);
            System.out.println("----------------------------------------");
            System.out.println("Type menu to reopen the options");
            System.out.println("Please separate operands, brackets, and operators with a space");
            System.out.println("----------------------------------------");
            s1 = new SimpleCalculatorUI();
        }
    }

    private void displayOptions() {
        System.out.println("----------------------------------------");
        System.out.println("Select one of the following operations:");
        System.out.println("1. Simple calculations");
        System.out.println("2. Polynomial calculations");
        System.out.println("3. Calculus calculations");
        System.out.println("Type anything to Exit");
        System.out.println("----------------------------------------");
        System.out.print("Input: ");
    }

}
