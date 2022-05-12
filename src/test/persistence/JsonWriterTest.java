package persistence;

import exception.NoTaskTimeException;
import model.StudyGoals;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Unit test for JsonWriter class
// Citation: code referenced from JsonReaderTest.java package in JsonSerializationDemo
public class JsonWriterTest extends JsonTest{
    private Task science;
    private Task business;

    @BeforeEach
    void runBefore() {
        try {
            science = new Task("science", 2);
            business = new Task("business", 4,true);
        } catch (NoTaskTimeException e) {
            fail();
        }
    }

    @Test
    void testWriterInvalidFile() {
        try {
            StudyGoals sg = new StudyGoals();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // hooray!
        }
    }

    @Test
    void testWriterEmptyStudyGoals() {
        try {
            StudyGoals sg = new StudyGoals();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(sg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            sg = reader.read();
            assertEquals(0, sg.numTasks());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            StudyGoals sg = new StudyGoals();
            sg.addTask(science);
            sg.addTask(business);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(sg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            sg = reader.read();
            List<Task> tasks = sg.showAllTasks();
            assertEquals(2, tasks.size());
            checkTask("science", 2, false, tasks.get(0));
            checkTask("business", 4, true, tasks.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
