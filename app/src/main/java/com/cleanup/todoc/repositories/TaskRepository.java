package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDAO;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {

    private final TaskDAO taskDAO;

    public TaskRepository(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public LiveData<List<Task>> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    public void createTask(Task task) {
        taskDAO.insertTask(task);
    }

    public void updateTask(Task task) {taskDAO.updateTask(task);}

    public void deleteTask(Task task) {
        taskDAO.deleteTask(task);
    }
}
