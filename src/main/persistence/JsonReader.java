package persistence;

import model.StudyGoals;
import model.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads task-list from JSON data stored in file
// Citation: code referenced from JsonReader.java package in JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads study-goals from file and returns it;
    // throws IOException if an error occurs reading data from file
    public StudyGoals read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStudyGoals(jsonObject);
    }

    // EFFECTS: reads study-goals source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder studyGoalsContent = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(str -> studyGoalsContent.append(str));
        }

        return studyGoalsContent.toString();
    }

    // EFFECTS: parses study-goals from JSON object and returns it
    private StudyGoals parseStudyGoals(JSONObject jsonObject) {
        StudyGoals sg = new StudyGoals();
        addGoals(sg, jsonObject);
        return sg;
    }

    // MODIFIES: sg
    // EFFECTS: parses goals from JSON object and adds them to study-goals
    private void addGoals(StudyGoals sg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("goals");
        for (Object json : jsonArray) {
            JSONObject nextGoal = (JSONObject) json;
            addGoal(sg, nextGoal);
        }
    }

    // MODIFIES: sg
    // EFFECTS: parses one goal from JSON object and adds it to study-goals
    private void addGoal(StudyGoals sg, JSONObject nextGoal) {
        String name = nextGoal.getString("name");
        int expectedTime = nextGoal.getInt("required time");
        boolean status = nextGoal.getBoolean("status");
        Task task = new Task(name, expectedTime,status);
        sg.addTask(task);
    }
}
