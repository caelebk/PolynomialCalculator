package ui.console;

import model.SimpleCalculator;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SimpleCalculatorUI {

    private static final String FILE = "./data/SimpleCalculator.txt";
    public SimpleCalculator s1;
    public Scanner input;
    public String command;
    public boolean currentlyRunning;
    public Stack<String> expression;

    public SimpleCalculatorUI() {
        runSimpleCalculator();
    }

    private void runSimpleCalculator() {
        input = new Scanner(System.in);
        currentlyRunning = true;
        System.out.println("Type load to load saved expression");
        while (currentlyRunning) {
            System.out.print("Input: ");
            command = input.nextLine();
            processCommand(command);
        }
    }

    //EFFECTS: It will take in the user input (String) and convert it into a mathematical statement.
    private void processCommand(String in) {
        s1 = new SimpleCalculator(in);
        if (in.equals("menu")) {
            System.out.println("Display menu that does not currently exist");
        } else if (command.equals("load")) {
            loadExpression();
            System.out.println("The previous expression saved was: " +  s1.getExpression());
            System.out.println(s1.getExpression() + " = " + s1.eval());
        } else if (s1.valid()) {
            System.out.println("Answer is: " + s1.eval() + " Save expression?");
            System.out.print("Save? ");
            command = input.nextLine();
            if (command.toLowerCase().equals("yes") || command.toLowerCase().equals("save")) {
                saveExpression();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Expression from FILE, if that file exists;
    private void loadExpression() {
        try {
            List<SimpleCalculator> simpleCalculators = Reader.readExpressions(new File(FILE));
            s1 = simpleCalculators.get(0);
        } catch (IOException e) {
            System.out.println("Failed to load");
        }
    }

    private void saveExpression() {
        try {
            Writer writer = new Writer(new File(FILE));
            writer.write(s1);
            writer.close();
            System.out.println("Accounts saved to file " + FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find " + FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }






}
