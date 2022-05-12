package ui.tools;

import exception.NoTaskTimeException;
import model.Task;
import ui.BorderPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Add button class
public class AddTool extends Tool {

    // Constructor
    public AddTool(BorderPanel panel, JComponent parent) {
        super(panel, parent);
    }

    // MODIFIES: this
    // EFFECTS: makes button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add");
        button = customizeButton(button);
        button.setPreferredSize(new Dimension(1000,50));
        button.setBackground(Color.pink);
        button.setOpaque(true);
    }

    // MODIFIES: this, parent
    // EFFECTS: adds button to the parent panel
    @Override
    public void addToParent(JComponent parent) {
        parent.add(button);

    }

    // MODIFIES: this
    // EFFECTS: adds listener
    @Override
    protected void addListener() {
        AddToolClickHandler addToolClickHandler = new AddToolClickHandler(button);
        button.setActionCommand("Add");
        button.addActionListener(addToolClickHandler);
        button.setEnabled(false);

        JTextField name = panel.getTaskName();
        name.addActionListener(addToolClickHandler);
        name.getDocument().addDocumentListener(addToolClickHandler);

        JTextField time = panel.getNumSessions();
        time.addActionListener(addToolClickHandler);
        time.getDocument().addDocumentListener(addToolClickHandler);
    }

    //This listener is shared by the text field and the add button.
    private class AddToolClickHandler extends ClickHandler implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;
        private JTextField taskName = panel.getTaskName();
        private JTextField numSes = panel.getNumSessions();

        // Constructor
        public AddToolClickHandler(JButton button) {
            this.button = button;
        }

        // MODIFIES: this, BorderPanel.this
        // EFFECTS: adds task to StudyGoals and ListModel
        public void actionPerformed(ActionEvent e) {

            String name = taskName.getText();
            String time = numSes.getText();

            //User didn't type in a unique name...
            if (name.equals("") || time.equals("") || alreadyInList(makeString(name,time))) {
                Toolkit.getDefaultToolkit().beep();
                numSes.requestFocusInWindow();
                numSes.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            try {
                studyGoals.addTask(new Task(name, Integer.parseInt(time)));
                listModel.insertElementAt(makeString(name, time), index);
            } catch (NoTaskTimeException e1) {
                JOptionPane.showMessageDialog(null, "Task duration can't be zero or below");
            }

            //Reset the text field.
            reset(numSes,taskName,list,index);
        }

        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // MODIFIES: this
        // EFFECTS: enables button
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // MODIFIES: this
        // EFFECTS: handles empty text field
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }
}
