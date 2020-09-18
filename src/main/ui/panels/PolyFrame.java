package ui.panels;

import persistence.PolyReader;

import javax.swing.*;
import java.awt.*;

public class PolyFrame extends JFrame {

    private PolyPanel poly;

    //EFFECTS: Constructs the Polynomial Calculator Window
    public PolyFrame() {
        super("Polynomial Derivative Application");
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
        JLabel label = new JLabel("Polynomial Derivatives");
        title.add(label);
        title.setBackground(Color.lightGray);
        add(title, BorderLayout.NORTH);
        poly = new PolyPanel();
        add(poly);
    }
}
