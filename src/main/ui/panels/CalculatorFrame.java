package ui.panels;

import javax.swing.*;
import java.io.File;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

//Constructs a main menu of calculators to select from.
public class CalculatorFrame extends JFrame implements ActionListener {

    private static final String fileName = "/Users/caeleb/IdeaProjects/project_a8a3b/data/sound.wav";

    //EFFECTS: Sets up the window of the calculator
    public CalculatorFrame() {
        super("Calculator Application");
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
        JPanel title = new JPanel();
        JPanel options = new JPanel();
        JLabel label = new JLabel("Simple Calculator");
        options.setLayout(new GridLayout(2, 1, 0, 40));
        title.add(label);
        title.setBackground(Color.lightGray);
        options.add(createButton("Simple Calculator", "simple"));
        options.add(createButton("Simple Polynomial Derivative", "poly"));
        add(title, BorderLayout.NORTH);
        add(options);
    }

    //EFFECTS: Creates buttons to put on the window
    private JButton createButton(String msg, String cmd) {
        JButton btn = new JButton(msg);
        btn.setActionCommand(cmd);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(this);
        return btn;
    }

    //EFFECTS: Plays sound
    public void playSound() {
        try {
            URL sound = new File(fileName).toURI().toURL();
            java.applet.AudioClip clip = java.applet.Applet.newAudioClip(sound);
            clip.play();
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(this, "Sound has failed");
        }

    }

    @Override
    //EFFECTS: Based on the button pressed chooses which calculator to make.
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("simple")) {
            playSound();
            new SimpleFrame();
        } else if (e.getActionCommand().equals("poly")) {
            playSound();
            new PolyFrame();
        }
    }
}
