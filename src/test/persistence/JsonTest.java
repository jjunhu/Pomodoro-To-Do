package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Superclass for JsonReaderTest and JsonWriterTest
// Citation: code referenced from JsonReaderTest.java package in JsonSerializationDemo
public class JsonTest {
    protected void checkTask(String name, int expectedTime, boolean status, Task task) {
        assertEquals(name, task.getTaskName());
        assertEquals(expectedTime, task.getTaskDuration());
        assertEquals(status,task.isCompleted());
    }
}