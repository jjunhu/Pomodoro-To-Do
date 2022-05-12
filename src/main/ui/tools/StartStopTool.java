package ui.tools;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import ui.BorderPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class StartStopTool extends Tool {
    JToggleButton button2;

    // Constructor
    public StartStopTool(BorderPanel panel, JComponent parent) {
        super(panel, parent);
        button2.setBackground(Color.pink);
        button2.setOpaque(true);
    }

    // MODIFIES: this
    // EFFECTS: adds listener
    @Override
    protected void addListener() {
        button2.addActionListener(new StartStopClickHandler());
    }

    // MODIFIES: this
    // EFFECTS: creates button
    @Override
    protected void createButton(JComponent parent) {
        button2 = new JToggleButton("Start");
    }

    // MODIFIES: this, parent
    // EFFECTS: adds to parent
    @Override
    public void addToParent(JComponent parent) {
        parent.add(button2);
    }

    // Listener class for the start/stop button
    private class StartStopClickHandler implements ActionListener {
        InputStream music;
        AudioStream audio;

        // MODIFIES: this, BorderPanel.this
        // EFFECTS: starts timer and plays music when button is selected, otherwise stops timer and stops music
        @Override
        public void actionPerformed(ActionEvent e) {
            if (button2.isSelected()) {
                button2.setText("Stop");
                playMusic("./data/studymusic.wav");
                panel.getTimer().start();
            } else {
                button2.setText("Start");
                stopMusic("./data/studymusic.wav");
                panel.getTimer().stop();
            }
        }

        // EFFECTS: plays music
        private void playMusic(String filepath) {
            try {
                music = new FileInputStream(new File(filepath));
                audio = new AudioStream(music);
                AudioPlayer.player.start(audio);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }

        // EFFECTS: stops music
        private void stopMusic(String filepath) {
            AudioPlayer.player.stop(audio);
        }
    }
}
