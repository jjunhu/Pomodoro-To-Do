package ui.tools;

import model.StudyGoals;
import ui.BorderPanel;

import javax.swing.*;
import java.awt.*;

// Abstract class for buttons
public abstract class Tool {

    protected JButton button;
    protected BorderPanel panel;

    // Constructor
    public Tool(BorderPanel panel, JComponent parent) {
        this.panel = panel;
        createButton(parent);
        addToParent(parent);
        addListener();
    }

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // Abstract class for all listeners
    abstract class ClickHandler {
        JList list = panel.getJList();
        DefaultListModel listModel = panel.getListModel();
        StudyGoals studyGoals = panel.getStudyGoals();

        // EFFECTS: makes universal way of representing strings in ListModel
        protected String makeString(String name, String time) {
            String s = "                                                            ";
            return "Task: " + name + s + "sessions left: " + time;
        }

        // MODIFIES: numSes, taskName, list
        // EFFECTS: resets fields and selection
        protected void reset(JTextField numSes, JTextField taskName, JList list, int index) {
            numSes.setText("");
            taskName.setText("");

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        // EFFECTS: returns taskName
        protected String getTaskName(String longString) {
            int start = "Task: ".length();
            int end = "                                                            sessions left: x".length();
            // no more than 9 sessions
            int mid = longString.length() - end;
            return longString.substring(start, mid);
        }

        // EFFECTS: returns taskTime
        protected String getTaskTimeLeft(String longString, int taskLength) {
            int start = "Task: ".length();
            int end = "                                                            sessions left: ".length();
            // no more than 9 sessions
            int allExceptTime = start + end + taskLength;
            return longString.substring(allExceptTime);
        }
    }
}
