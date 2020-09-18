package ui.panels;


import javax.swing.*;

import java.awt.*;


//Constructs UI for the simple calculator
public class SimpleFrame extends JFrame {

    private SimplePanel sp;

    //EFFECTS: Constructs the Simple Calculator Window
    public SimpleFrame() {
        super("Simple Calculator Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        createIntialFrame();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    //EFFECTS: Builds the main menu of the calculator with components
    private void createIntialFrame() {
        setLayout(new BorderLayout());
        JPanel title = new JPanel();
        JLabel label = new JLabel("Simple Calculator");
        title.add(label);
        title.setBackground(Color.lightGray);
        add(title, BorderLayout.NORTH);
        sp = new SimplePanel();
        add(sp);
    }

}
