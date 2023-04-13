package com.cleanup.todoc.injections;

import android.content.Context;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static ProjectRepository getProjectRepository(Context context) {
        TodocDatabase db = TodocDatabase.getInstance(context);
        return new ProjectRepository(db.projectDAO());
    }

    public static TaskRepository getTaskRepository(Context context) {
        TodocDatabase db = TodocDatabase.getInstance(context);
        return new TaskRepository(db.taskDAO());
    }

    public static Executor getExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory getViewModelFactory(Context context) {
        ProjectRepository projectRepository = getProjectRepository(context);
        TaskRepository taskRepository = getTaskRepository(context);
        Executor executor = getExecutor();
        return new ViewModelFactory(projectRepository, taskRepository, executor);
    }

}
