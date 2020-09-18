package ui.panels;

import model.PolynomialCalculator;
import persistence.PolyReader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class PolyPanel extends JPanel implements ActionListener {

    private static final String FILE = "./data/PolynomialCalculator.txt";
    private PolynomialCalculator p1;
    private JTextField input;
    private JTextField output;
    private JLabel tempLabel;

    //EFFECTS: Constructs the calculator panel
    public PolyPanel() {
        JLabel label = new JLabel("Polynomials must be in the form: ax^n + b^n + c");
        JLabel label1 = new JLabel("Coefficients must always appear. And no trig implementation");
        JLabel label2 = new JLabel("The answer is:");
        tempLabel = new JLabel("");
        JPanel sl = new JPanel();
        sl.add(tempLabel);
        input = new JTextField();
        input.setPreferredSize(new Dimension(200, 20));
        output = new JTextField();
        output.setPreferredSize(new Dimension(200, 150));
        JPanel config = new JPanel(new GridLayout(2,0,50, 0));
        config.add(createButton("Check", "check"));
        config.add(createButton("Derivative", "derive"));
        config.add(createButton("Save Input", "save"));
        config.add(createButton("Load", "load"));
        config.add(createButton("Evaluate", "eval"));
        add(label);
        add(label1);
        add(input);
        add(label2);
        add(output);
        add(config);
        add(sl);
    }

    //EFFECTS: Creates buttons to put on the window
    private JButton createButton(String msg, String cmd) {
        JButton btn = new JButton(msg);
        btn.setActionCommand(cmd);
        btn.addActionListener(this);
        return btn;
    }

    //MODIFIES: this
    //EFFECTS: Evaluates the inputted field expression and produces the output in the output field
    private void checkExpression() {
        try {
            String exp = input.getText();
            p1 = new PolynomialCalculator(exp);
            if (p1.valid()) {
                output.setText(exp);
                JOptionPane.showMessageDialog(this, "It meets the criteria!");
            } else {
                output.setText("Invalid expression!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nothing Inputted");
        }
    }

    //EFFECTS: Saves the expression in the input textfield
    private void saveExpression() {
        try {
            p1 = new PolynomialCalculator(input.getText());
            Writer writer = new Writer(new File(FILE));
            writer.write(p1);
            writer.close();
            tempLabel.setText("The Expression has been Saved!");
        } catch (FileNotFoundException e) {
            tempLabel.setText("Unable to save to non-existent directory.");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nothing Inputted");
        }
    }

    //MODIFIES: this
    //EFFECTS: Loads the expression from the file and evaluates it
    private void loadExpression() {
        try {
            List<PolynomialCalculator> polynomialCalculators = PolyReader.readExpressions(new File(FILE));
            p1 = polynomialCalculators.get(0);
            tempLabel.setText("The Expression has been Loaded!");
            input.setText(p1.getPolyExp());
            //outputExpression();
        } catch (IOException e) {
            tempLabel.setText("The Expression has Failed to Load!");
            JOptionPane.showMessageDialog(this, "ERROR: Could not find file.");
        }
    }

    //MODIFIES: this
    //EFFECTS: Finds the derivative
    private void derive() {
        if (p1 != null && p1.valid()) {
            p1.derivative();
            output.setText(p1.getPolyExp());
            input.setText(p1.getPolyExp());
        } else {
            JOptionPane.showMessageDialog(this, "Nothing Inputted");
        }
    }

    //EFFECTS: Displays polynomial evaluated at a point
    private void evaluate() {
        try {
            p1 = new PolynomialCalculator(input.getText());
            if (p1 == null || !p1.valid()) {
                JOptionPane.showMessageDialog(this, "input a valid polynomial");
            } else {
                double x = Double.parseDouble(JOptionPane.showInputDialog(
                        "f(x) = " + p1.getPolyExp() + "\nChoose an x value"));
                JOptionPane.showMessageDialog(this, "f(" + x + ") = "
                        + p1.eval(x));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Syntax");
        }
    }

    //EFFECTS: based on which button is pressed a certain operation is done.
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "check":
                checkExpression();
                break;
            case "save":
                saveExpression();
                break;
            case "load":
                loadExpression();
                break;
            case "derive":
                derive();
                break;
            case "eval":
                evaluate();
                break;
        }
    }
}
