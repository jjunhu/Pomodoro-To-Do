package ui;

import model.StudyGoals;
import org.omg.PortableInterceptor.ACTIVE;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import ui.tools.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

// Represents panel with tasks
public class BorderPanel extends javax.swing.JPanel implements ListSelectionListener {
    private static final int ONE_SECOND = 1000;
    protected JList list;
    protected DefaultListModel listModel;
    protected StudyGoals sg;
    protected Timer timer;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;
    protected static final String JSON_STORE = "./data/study-goals.json";
    private JTextField taskName;
    private JTextField numSessions;
    private JLabel timeDisplay;

    private List<Tool> tools;

    // Constructor
    public BorderPanel() {
        super(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        initializeFields();

        setupTimerNorthPane();

        setUpCenterPane();

        setUpButtonSouthPane();
    }

    // MODIFIES: this
    // EFFECTS: initializes fields of the class
    private void initializeFields() {
        setUpTextFields();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        tools = new ArrayList<Tool>();

        //Create a timer display
        timeDisplay = new JLabel("");
        timeDisplay.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        timeDisplay.setForeground(Color.WHITE);
        timeDisplay.setBackground(Color.pink);
        timeDisplay.setOpaque(true);
        timeDisplay.setFont(new Font("Calibri",Font.BOLD,96));

        timer = new Timer(ONE_SECOND,new CountDownListener()); // update every second

        sg = new StudyGoals();
        listModel = new DefaultListModel();

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setFont(new Font("Times",Font.ITALIC,24));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);

        list.setBackground(Color.pink);
        list.setForeground(Color.white);
        list.setOpaque(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up JTextFields
    private void setUpTextFields() {
        taskName = new JTextField("(input your task here)");
        taskName.setBackground(Color.WHITE);
        taskName.setOpaque(true);
        numSessions = new JTextField("(input single-digit desired number of sessions)");
        numSessions.setBackground(Color.WHITE);
        numSessions.setOpaque(true);
    }

    // MODIFIES: this
    // EFFECTS: adds timer to the north border
    private void setupTimerNorthPane() {
        add(timeDisplay, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: adds button panel to the south border
    private void setUpButtonSouthPane() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0,1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);

        StartStopTool startStopTool = new StartStopTool(this,toolArea);
        tools.add(startStopTool);

        toolArea.add(taskName);
        toolArea.add(numSessions);

        AddTool addTool = new AddTool(this, toolArea);
        tools.add(addTool);

        RemoveTool removeTool = new RemoveTool(this, toolArea);
        tools.add(removeTool);

        RetrieveTool retrieveTool = new RetrieveTool(this, toolArea);
        tools.add(retrieveTool);

        SaveTool saveTool = new SaveTool(this, toolArea);
        tools.add(saveTool);

        SetCompleteTool setCompleteTool = new SetCompleteTool(this, toolArea);
        tools.add(setCompleteTool);
    }

    // MODIFIES: this
    // EFFECTS: adds scroll list of tasks to the center
    private void setUpCenterPane() {
        JScrollPane listScrollPane = new JScrollPane(list);
        add(listScrollPane, BorderLayout.CENTER);
    }


    // Simple getters
    public StudyGoals getStudyGoals() {
        return sg;
    }

    public DefaultListModel getListModel() {
        return listModel;
    }

    public JList getJList() {
        return list;
    }

    public JTextField getTaskName() {
        return taskName;
    }

    public JTextField getNumSessions() {
        return numSessions;
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }

    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    public Timer getTimer() {
        return timer;
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
    }

    // Listener class for the timer
    private class CountDownListener implements ActionListener {
        private double timeLeft = 3000000; // 50 minutes
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");

        // MODIFIES: this, BorderPanel.this
        // EFFECTS: negatively increments the timer, plays the alarm
        @Override
        public void actionPerformed(ActionEvent e) {
            timeLeft -= 1000;
            if (timeLeft <= 0) {
                timer.stop();
                playAlarm("./data/alarm.wav");
            } else {
                timeDisplay.setText(df.format(timeLeft));
            }
        }

        // EFFECTS: plays alarm
        private void playAlarm(String filepath) {
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
}

