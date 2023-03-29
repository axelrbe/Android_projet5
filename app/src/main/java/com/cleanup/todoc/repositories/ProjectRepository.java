package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDAO;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectRepository {

    private final ProjectDAO projectDAO;

    public ProjectRepository(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    public LiveData<List<Project>> getAllProjects() {
        return projectDAO.getAllProjects();
    }
}
