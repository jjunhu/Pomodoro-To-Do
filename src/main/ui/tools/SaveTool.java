package ui.tools;

import model.StudyGoals;
import persistence.JsonWriter;
import ui.BorderPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Save button class
public class SaveTool extends Tool {

    // Constructor
    public SaveTool(BorderPanel panel, JComponent parent) {
        super(panel, parent);
    }

    // MODIFIES: this
    // EFFECTS: adds listener
    @Override
    protected void addListener() {
        button.setActionCommand("Save");
        button.addActionListener(new SaveClickHandler());
    }

    // MODIFIES: this
    // EFFECTS: creates button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save");
        button = customizeButton(button);
        button.setBackground(Color.pink);
        button.setOpaque(true);
    }

    // Listener for save button
    private class SaveClickHandler extends ClickHandler implements ActionListener {
        // MODIFIES: this, BorderPanel.this
        // EFFECTS: saves current goals to Json
        @Override
        public void actionPerformed(ActionEvent e) {
            JsonWriter jsonWriter = panel.getJsonWriter();
            StudyGoals sg = panel.getStudyGoals();
            try {
                jsonWriter.open();
                jsonWriter.write(sg);
                jsonWriter.close();
            } catch (FileNotFoundException g) {
                // do nothing
            }
        }
    }
}
