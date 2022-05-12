package ui.tools;

import model.StudyGoals;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import ui.BorderPanel;

import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

// Complete button class
public class SetCompleteTool extends Tool {

    // Constructor
    public SetCompleteTool(BorderPanel panel, JComponent parent) {
        super(panel, parent);
    }

    // MODIFIES: this
    // EFFECTS: adds listener
    @Override
    protected void addListener() {
        button.setActionCommand("Complete");
        button.addActionListener(new SetCompleteToolClickHandler());
    }

    // MODIFIES: this
    // EFFECTS: creates button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Complete");
        customizeButton(button);
        button.setBackground(Color.pink);
        button.setOpaque(true);
    }

    // Listener for complete button
    private class SetCompleteToolClickHandler extends ClickHandler implements ActionListener {
        // MODIFIES: this, BorderPanel.this
        // EFFECTS: completes task and displays the -1 numSession on ListModel
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and complete whatever is selected.

            int index = list.getSelectedIndex();

            String taskName = getTaskName(listModel.get(index).toString());
            studyGoals.completeTask(taskName);
            int taskTime = Integer.parseInt(getTaskTimeLeft(listModel.get(index).toString(), taskName.length()));
            if (taskTime >= 1) {
                listModel.setElementAt(makeString(taskName, String.valueOf(taskTime - 1)), index);
                playMusic("./data/cheering.wav");
            }
        }
    }

    // EFFECTS: plays music when one session is completed
    private void playMusic(String filepath) {
        InputStream music;
        try {
            music = new FileInputStream(new File(filepath));
            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}
