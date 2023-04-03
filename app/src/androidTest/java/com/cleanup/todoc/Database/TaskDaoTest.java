package com.cleanup.todoc.Database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TodocDatabase mTodocDatabase;
    private final Project[] projects = Project.getAllProjects();
    private final Task task1 = new Task(projects[0].getId(), projects[0].getId(),"Task_Test 1", new Date().getTime());
    private final Task task2 = new Task(projects[1].getId(), projects[0].getId(),"Task_Test 1", new Date().getTime());
    private final Task task3 = new Task(projects[2].getId(), projects[1].getId(),"Task_Test 1", new Date().getTime());

    @Before
    public void initDatabase() throws InterruptedException {
        this.mTodocDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                        TodocDatabase.class)
                .allowMainThreadQueries()
                .build();

        this.mTodocDatabase.projectDAO().insertAll(Project.getAllProjects());

        List<Task> allTasks = LiveDataTestUtil.getValue(this.mTodocDatabase.taskDAO().getAllTasks());
        assertTrue(allTasks.isEmpty());
    }

    @After
    public void closeDatabase() {
        this.mTodocDatabase.close();
    }

    @Test
    public void taskManagement_Insert() throws InterruptedException {
        List<Project> allProjects = LiveDataTestUtil.getValue(this.mTodocDatabase.projectDAO().getAllProjects());

        this.mTodocDatabase.taskDAO().insertTask(this.task1);
        this.mTodocDatabase.taskDAO().insertTask(this.task2);

        List<Task> allTasks = LiveDataTestUtil.getValue(this.mTodocDatabase.taskDAO().getAllTasks());
        assertEquals(2, allTasks.size());

        this.mTodocDatabase.taskDAO().insertTask(this.task3);

        allTasks = LiveDataTestUtil.getValue(this.mTodocDatabase.taskDAO().getAllTasks());
        assertEquals(3, allTasks.size());

        assertEquals(allProjects.get(0).getId(), allTasks.get(0).getProjectId());
        assertEquals(allProjects.get(1).getId(), allTasks.get(1).getProjectId());
        assertEquals(allProjects.get(2).getId(), allTasks.get(2).getProjectId());
    }

    @Test
    public void taskManagement_Delete() throws InterruptedException {
        List<Project> allProjects = LiveDataTestUtil.getValue(this.mTodocDatabase.projectDAO().getAllProjects());

        this.mTodocDatabase.taskDAO().insertTask(this.task1);
        this.mTodocDatabase.taskDAO().insertTask(this.task2);
        this.mTodocDatabase.taskDAO().insertTask(this.task3);

        List<Task> allTasks = LiveDataTestUtil.getValue(this.mTodocDatabase.taskDAO().getAllTasks());
        assertEquals(3, allTasks.size());

        this.mTodocDatabase.taskDAO().deleteTask(allTasks.get(2));
        allTasks = LiveDataTestUtil.getValue(this.mTodocDatabase.taskDAO().getAllTasks());

        assertEquals(2, allTasks.size());
    }
}
