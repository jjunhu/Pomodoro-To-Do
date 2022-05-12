package ui.tools;

import model.StudyGoals;
import ui.BorderPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Remove button class
public class RemoveTool extends Tool {

    // Constructor
    public RemoveTool(BorderPanel panel, JComponent parent) {
        super(panel, parent);
    }

    // MODIFIES: this
    // EFFECTS: makes button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Remove");
        button = customizeButton(button);
        button.setBackground(Color.pink);
        button.setOpaque(true);
    }

    // MODIFIES: this
    // EFFECTS: adds listener
    @Override
    protected void addListener() {
        button.setActionCommand("Remove");
        button.addActionListener(new RemoveToolClickHandler());
    }

    // Listener for remove button
    private class RemoveToolClickHandler extends ClickHandler implements ActionListener {

        // MODIFIES: this, BorderPanel.this
        // EFFECTS: removes task from ListModel and StudyGoals
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever is selected.

            int index = list.getSelectedIndex();

            studyGoals.remove(getTaskName(listModel.get(index).toString()));
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable removing.
//                button.setEnabled(false);

            } else { //Select an index.
                if (index == size) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }
}
