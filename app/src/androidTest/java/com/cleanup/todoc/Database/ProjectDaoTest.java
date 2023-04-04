package com.cleanup.todoc.Database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.ProjectDAO;
import com.cleanup.todoc.model.Project;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();
    private TodocDatabase mTodocDatabase;

    @Before
    public void initializeDatabase() {
        this.mTodocDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void clearDatabase() {
        this.mTodocDatabase.close();
    }

    @Test
    public void projectManagement_Insert() throws InterruptedException {
        List<Project> allProjects = LiveDataTestUtil.getValue(this.mTodocDatabase.projectDAO().getAllProjects());
        assertTrue(allProjects.isEmpty());

        this.mTodocDatabase.projectDAO().insertAll(Project.getAllProjects());
        allProjects = LiveDataTestUtil.getValue(this.mTodocDatabase.projectDAO().getAllProjects());


        assertEquals(allProjects.get(0).getName(), Project.getAllProjects()[0].getName());
        assertEquals(allProjects.get(0).getName(), Project.getAllProjects()[0].getName());
        assertEquals(allProjects.get(0).getName(), Project.getAllProjects()[0].getName());

        assertEquals(allProjects.get(1).getName(), Project.getAllProjects()[1].getName());
        assertEquals(allProjects.get(1).getName(), Project.getAllProjects()[1].getName());
        assertEquals(allProjects.get(1).getName(), Project.getAllProjects()[1].getName());

        assertEquals(allProjects.get(2).getName(), Project.getAllProjects()[2].getName());
        assertEquals(allProjects.get(2).getName(), Project.getAllProjects()[2].getName());
        assertEquals(allProjects.get(2).getName(), Project.getAllProjects()[2].getName());
    }
}
