package ui.tools;

import exception.NoTaskTimeException;
import model.StudyGoals;
import model.Task;
import ui.BorderPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Retrieve button class
public class RetrieveTool extends Tool {

    // Constructor
    public RetrieveTool(BorderPanel panel, JComponent parent) {
        super(panel, parent);
    }

    // MODIFIES: this
    // EFFECTS: adds listener
    @Override
    protected void addListener() {
        button.setActionCommand("Retrieve");
        button.addActionListener(new RetrieveClickHandler());
    }

    // MODIFIES: this
    // EFFECTS: creates button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Retrieve");
        button = customizeButton(button);
        button.setBackground(Color.pink);
        button.setOpaque(true);
    }

    // Listener for retrieve button
    private class RetrieveClickHandler extends ClickHandler implements ActionListener {
        // MODIFIES: this, BorderPanel.this
        // EFFECTS: retrieves task and displays it in ListModel
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField taskName = panel.getTaskName();
            JTextField numSes = panel.getNumSessions();

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            try {
                StudyGoals sg = panel.getJsonReader().read();
                for (Task t : sg.showAllTasks()) {
                    String name = t.getTaskName();
                    String time = Integer.toString(t.getTaskDuration());
                    listModel.insertElementAt(makeString(name, time), index);
                    studyGoals.addTask(new Task(name, Integer.parseInt(time)));

                    //Reset the text field.
                    reset(numSes, taskName, list, index);
                }
            } catch (NoTaskTimeException | IOException e1) {
                JOptionPane.showMessageDialog(null, "Task duration can't be zero or below");
            }
        }
    }
}
