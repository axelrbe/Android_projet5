package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final Executor executor;

    public TaskViewModel(ProjectRepository projectRepository, TaskRepository taskRepository, Executor executor) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.executor = executor;
    }

    private LiveData<List<Project>> allProjects;

    public void initList() {
        if (allProjects == null) {
            allProjects = projectRepository.getAllProjects();
        }
    }

    public LiveData<List<Project>> getAllProjects() {
        return allProjects;
    }

    public LiveData<List<Task>> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public void createTask(Task task) {
        executor.execute(() -> taskRepository.createTask(task));
    }

    public void deleteTask(Task task) {
        executor.execute(() -> taskRepository.deleteTask(task));
    }
}
