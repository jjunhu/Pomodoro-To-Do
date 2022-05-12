package model;

import exception.NoTaskTimeException;
import org.json.JSONObject;

// Represents individual tasks having a task name, a duration, and a completed? status
public class Task {
    private String taskName;        // name of study task
    private int expectedSessions;   // duration of task, how many 50-minute sessions?
    private boolean completed;      // status - is it completed?

    // EFFECTS: constructs a task with given name and number of sessions, set to non-completed;
    // if num hours is <= 0, throws NoTaskTimeException
    public Task(String task, int num) throws NoTaskTimeException {
        if (num <= 0) {
            throw new NoTaskTimeException();
        }
        taskName = task;
        expectedSessions = num;
        completed = false;
    }

    // REQUIRES: taskName has a non-zero length, hours is > 0
    // EFFECTS: constructs a task with given name and number of sessions, as well as completion status
    public Task(String task, int num, boolean status) {
        taskName = task;
        expectedSessions = num;
        completed = status;
    }

    // EFFECTS: returns the task's name
    public String getTaskName() {
        return taskName;
    }

    // EFFECTS: returns expected number of 50-minute sessions for this task
    public int getTaskDuration() {
        return expectedSessions;
    }

    // EFFECTS: returns if task is completed
    public boolean isCompleted() {
        return completed;
    }

    // MODIFIES: this
    // EFFECTS: if expectedSessions == 1, marks task as complete, otherwise expectedSession --
    public void completeTask() {
        if (expectedSessions == 1) {
            expectedSessions--;
            completed = true;
        } else if (expectedSessions > 1) {
            expectedSessions--;
        }
    }

    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", taskName);
        json.put("required time", expectedSessions);
        json.put("status", completed);
        return json;
    }
}
