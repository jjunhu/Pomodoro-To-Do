package model;

import exception.NoTaskTimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for StudyGoals class
public class StudyGoalsTest {
    private StudyGoals studyGoals;

    @BeforeEach
    void runBefore() {
        studyGoals = new StudyGoals();
    }

    @Test
    void testAddTaskNotExpectingException() {
        try {
            Task a = new Task("1",1);
            studyGoals.addTask(a);
            assertTrue(studyGoals.containsTask(a));
            assertEquals(1, studyGoals.numTasks());
        } catch (NoTaskTimeException e) {
            fail();
        }
    }

    @Test
    void testRemove() {
        try {
            Task a = new Task("1",1);
            studyGoals.addTask(a);
            studyGoals.remove(a.getTaskName());
            assertFalse(studyGoals.containsTask(a));
            assertEquals(0, studyGoals.numTasks());
            studyGoals.addTask(a);
            studyGoals.remove("2");
            assertEquals(1,studyGoals.numTasks());
        } catch (NoTaskTimeException e) {
            fail();
        }
    }

    @Test
    void testCompleteTask() {
        try {
            Task a = new Task("1",1);
            studyGoals.addTask(a);
            studyGoals.completeTask(a.getTaskName());
            assertTrue(a.isCompleted());
            Task b = new Task("2",1);
            studyGoals.completeTask(b.getTaskName());
            assertFalse(b.isCompleted());
        } catch (NoTaskTimeException e) {
            fail();
        }
    }

    @Test
    void testShowAllTasks() {
        try {
            Task a = new Task("1",1);
            Task b = new Task("2", 2);
            Task c = new Task("3", 3);
            studyGoals.addTask(a);
            studyGoals.addTask(b);
            studyGoals.addTask(c);
            ArrayList<Task> allTasks = studyGoals.showAllTasks();
            assertTrue(allTasks.contains(a) && allTasks.contains(b) && allTasks.contains(c));
            assertEquals(3,allTasks.size());
        } catch (NoTaskTimeException e) {
            fail();
        }
    }

    @Test
    void testShowNextTasksEmpty() {
        assertEquals(new ArrayList<>(), studyGoals.showNextTasks());
    }

    @Test
    void testShowNextTasksAllCompleted() {
        try {
            Task a = new Task("1",1);
            a.completeTask();
            Task b = new Task("2", 1);
            b.completeTask();
            Task c = new Task("3", 1);
            c.completeTask();
            studyGoals.addTask(a);
            studyGoals.addTask(b);
            studyGoals.addTask(c);
            assertEquals(0,studyGoals.showNextTasks().size());
        } catch (NoTaskTimeException e) {
            fail();
        }
    }

    @Test
    void testShowNextTasksNonCompleted() {
        try {
            Task a = new Task("1",1);
            Task b = new Task("2", 2);
            Task c = new Task("3", 3);
            studyGoals.addTask(a);
            studyGoals.addTask(b);
            studyGoals.addTask(c);
            ArrayList<Task> unCompletedTasks = studyGoals.showNextTasks();
            assertTrue(unCompletedTasks.contains(a) && unCompletedTasks.contains(b)
                    && unCompletedTasks.contains(c));
            assertEquals(3, studyGoals.showNextTasks().size());
        } catch (NoTaskTimeException e) {
            fail();
        }
    }

    @Test
    void testShowNextTasksMixOfBoth() {
        try {
            Task a = new Task("1",1);
            a.completeTask();
            Task b = new Task("2", 2);
            Task c = new Task("3", 3);
            c.completeTask();
            studyGoals.addTask(a);
            studyGoals.addTask(b);
            studyGoals.addTask(c);
            ArrayList<Task> unCompletedTasks = studyGoals.showNextTasks();
            assertFalse(unCompletedTasks.contains(a));
            assertTrue(unCompletedTasks.contains(b));
            assertTrue(unCompletedTasks.contains(c));
            assertEquals(2,unCompletedTasks.size());
        } catch (NoTaskTimeException e) {
            fail();
        }
    }

    @Test
    void testShowCompletedTasksEmpty() {
        assertEquals(new ArrayList<>(), studyGoals.showCompletedTasks());
    }

    @Test
    void testShowCompletedTasksAllCompleted() {
        try {
            Task a = new Task("1",1);
            a.completeTask();
            Task b = new Task("2", 1);
            b.completeTask();
            Task c = new Task("3", 1);
            c.completeTask();
            studyGoals.addTask(a);
            studyGoals.addTask(b);
            studyGoals.addTask(c);
            ArrayList<Task> completedTasks = studyGoals.showCompletedTasks();
            assertTrue(completedTasks.contains(a) && completedTasks.contains(b) && completedTasks.contains(c));
            assertEquals(3,completedTasks.size());
        } catch (NoTaskTimeException e) {
            fail();
        }
    }

    @Test
    void testShowCompletedTasksNonCompleted() {
        try {
            Task a = new Task("1",1);
            Task b = new Task("2", 2);
            Task c = new Task("3", 3);
            studyGoals.addTask(a);
            studyGoals.addTask(b);
            studyGoals.addTask(c);
            assertEquals(new ArrayList<>(), studyGoals.showCompletedTasks());
        } catch (NoTaskTimeException e) {
            fail();
        }
    }

    @Test
    void testShowCompletedTasksMixOfBoth() {
        try {
            Task a = new Task("1",1);
            a.completeTask();
            Task b = new Task("2", 2);
            Task c = new Task("3", 3);
            c.completeTask();
            studyGoals.addTask(a);
            studyGoals.addTask(b);
            studyGoals.addTask(c);
            ArrayList<Task> completedTasks = studyGoals.showCompletedTasks();
            assertTrue(completedTasks.contains(a) && !completedTasks.contains(c) && !completedTasks.contains(b));
            assertEquals(1,completedTasks.size());
        } catch (NoTaskTimeException e) {
            fail();
        }
    }

}

