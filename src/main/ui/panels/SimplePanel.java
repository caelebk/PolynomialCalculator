package ui.panels;

import model.SimpleCalculator;
import persistence.Reader;
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

//Constructs the input & output portion of simple calculator
public class SimplePanel extends JPanel implements ActionListener {

    private static final String FILE = "./data/SimpleCalculator.txt";
    private SimpleCalculator s1;
    private JTextField input;
    private JTextField output;
    private JLabel tempLabel;

    //EFFECTS: Constructs the calculator panel
    public SimplePanel() {
        JLabel label = new JLabel("Please separate operands and operators with a space,");
        JLabel label1 = new JLabel("This calculator does not support parenthesis");
        JLabel label2 = new JLabel("The answer is:");
        tempLabel = new JLabel("");
        JPanel sl = new JPanel();
        sl.add(tempLabel);
        input = new JTextField();
        input.setPreferredSize(new Dimension(200, 20));
        output = new JTextField();
        output.setPreferredSize(new Dimension(200, 200));
        JPanel config = new JPanel(new GridLayout(1,0,50, 0));
        config.add(createButton("Save", "save"));
        config.add(createButton("Load", "load"));
        add(label);
        add(label1);
        add(input);
        add(createButton("Evaluate", "eval"));
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
    private void outputExpression() {
        String exp = input.getText();
        s1 = new SimpleCalculator(exp);
        if (s1.valid()) {
            output.setText(exp + " = " + s1.eval());
        } else {
            output.setText("Invalid expression!");
        }
    }

    //EFFECTS: Saves the expression in the input textfield
    private void saveExpression() {
        s1 = new SimpleCalculator(input.getText());
        try {
            Writer writer = new Writer(new File(FILE));
            writer.write(s1);
            writer.close();
            tempLabel.setText("The Expression has been Saved!");
        } catch (FileNotFoundException e) {
            tempLabel.setText("Unable to save to non-existent directory.");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    //MODIFIES: this
    //EFFECTS: Loads the expression from the file and evaluates it
    private void loadExpression() {
        try {
            List<SimpleCalculator> simpleCalculators = Reader.readExpressions(new File(FILE));
            s1 = simpleCalculators.get(0);
            tempLabel.setText("The Expression has been Loaded!");
            input.setText(s1.getExpression());
            outputExpression();
        } catch (IOException e) {
            tempLabel.setText("The Expression has Failed to Load!");
            JOptionPane.showMessageDialog(this, "ERROR: Could not find file.");
        }
    }

    //EFFECTS: based on which button is pressed a certain operation is done.
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "eval":
                outputExpression();
                break;
            case "save":
                saveExpression();
                break;
            case "load":
                loadExpression();
                break;
        }
    }
}
