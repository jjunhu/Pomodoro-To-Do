package persistence;

import model.StudyGoals;
import model.Task;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Unit tests for JsonReader class
// Citation: code referenced from JsonReaderTest.java package in JsonSerializationDemo
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            StudyGoals wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // hooray!
        }
    }

    @Test
    void testReaderEmptyStudyGoals() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStudyGoals.json");
        try {
            StudyGoals sg = reader.read();
            assertEquals(0, sg.numTasks());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderExampleStudyGoals() {
        JsonReader reader = new JsonReader("./data/testReaderExampleStudyGoals.json");
        try {
            StudyGoals sg = reader.read();
            List<Task> tasks = sg.showAllTasks();
            assertEquals(2, tasks.size());
            checkTask("math", 2, true, tasks.get(0));
            checkTask("english", 3, false, tasks.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
