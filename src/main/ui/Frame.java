package ui;

import java.awt.*;
import javax.swing.*;

// Graphical interface for the app
public class Frame extends javax.swing.JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    // Constructor
    public Frame() {
        //Create and set up the window.
        super("Trackmodoro App");
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Create panel
        JComponent newContentPane = new BorderPanel();
        newContentPane.setOpaque(true); //content panes must be opaque
        add(newContentPane);

        //Display the window.
        pack();
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
    }

}