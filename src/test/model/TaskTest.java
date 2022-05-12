package model;

import exception.NoTaskTimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Task class
class TaskTest {
    private Task task1;
    private Task task2;

    @BeforeEach
    void runBefore() {
        try {
            task1 = new Task("1",1);
            task2 = new Task("2", 2,false);
        } catch (NoTaskTimeException e) {
            fail();
        }
    }

    @Test
    void testBuildTaskExpectingException() {
        try {
            Task a = new Task("1",0);
            fail();
        } catch (NoTaskTimeException e) {
            // hooray!
        }
    }

    @Test
    void testBuildTaskNotExpectingException() {
        try {
            Task a = new Task("1",2);
            assertEquals(2, a.getTaskDuration());
            assertEquals("1", a.getTaskName());
        } catch (NoTaskTimeException e) {
            fail();
        }
    }

    @Test
    void testGetTaskName() {
        assertEquals("1", task1.getTaskName());
    }

    @Test
    void testGetTaskDuration() {
        assertEquals(1, task1.getTaskDuration());
    }

    @Test
    void testIsCompleted() {
        assertFalse(task1.isCompleted());
        task1.completeTask();
        assertTrue(task1.isCompleted());
    }

    @Test
    void testCompleteTask() {
        task1.completeTask();
        assertTrue(task1.isCompleted());
        assertEquals(0,task1.getTaskDuration());
        task1.completeTask();
        assertTrue(task1.isCompleted());
    }

    @Test
    void testCompleteTask2() {
        task2.completeTask();
        assertFalse(task2.isCompleted());
        assertEquals(1,task2.getTaskDuration());
        task2.completeTask();
        assertTrue(task2.isCompleted());
        assertEquals(0,task2.getTaskDuration());
    }
}