package model;

import com.sun.corba.se.spi.ior.Writeable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

// Represents a collection of study tasks/goals one has completed as well as those one has not yet completed
public class StudyGoals {
    private ArrayList<Task> tasksList;

    // EFFECTS: builds an empty list of tasks to be worked on
    public StudyGoals() {
        tasksList = new ArrayList<>();
    }

    // REQUIRES: task t cannot be identical to one that is already in the list
    // MODIFIES: this
    // EFFECTS: adds a task to the to-do list
    public void addTask(Task t) {
        tasksList.add(t);
    }

    // MODIFIES: this
    // EFFECTS: removes a task from the to-do list
    public void remove(String taskName) {
        Task willBeRemoved = null;
        boolean removed = false;

        for (Task t : tasksList) {
            if (t.getTaskName().equals(taskName)) {
                willBeRemoved = t;
                removed = true;
            }
        }
        if (!removed) {
            return;
        } else {
            tasksList.remove(willBeRemoved);
        }
    }

    // MODIFIES: this
    // EFFECTS: completes a task from the taskList
    public void completeTask(String taskName) {
        for (Task task : tasksList) {
            if (task.getTaskName().equals(taskName)) {
                task.completeTask();
            }
        }
    }

    // EFFECTS: simple getter, returns number of tasks,
    public int numTasks() {
        return tasksList.size();
    }

    // EFFECTS: simple getter, produces true if task is in the list
    public Boolean containsTask(Task task) {
        return tasksList.contains(task);
    }

    // EFFECTS: simple getter, returns all tasks,
    public ArrayList<Task> showAllTasks() {
        return tasksList;
    }

    // EFFECTS: returns all non-completed tasks
    public ArrayList<Task> showNextTasks() {
        StudyGoals toBeWorkedOn = new StudyGoals();
        for (Task t : tasksList) {
            if (!t.isCompleted()) {
                toBeWorkedOn.addTask(t);
            }
        }
        return toBeWorkedOn.tasksList;
    }

    // EFFECTS: returns all completed tasks
    public ArrayList<Task> showCompletedTasks() {
        StudyGoals finished = new StudyGoals();
        for (Task t : tasksList) {
            if (t.isCompleted()) {
                finished.addTask(t);
            }
        }
        return finished.tasksList;
    }

    // EFFECTS: returns this task-list as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("goals", tasksToJson());
        return json;
    }

    // EFFECTS: returns tasks in this task-list as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : tasksList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
