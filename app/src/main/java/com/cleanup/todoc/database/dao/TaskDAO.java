package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDAO {

    @Query("SELECT * FROM Task WHERE projectId = :projectId")
    public LiveData<List<Task>> getTasks(long projectId);

    @Query("SELECT * FROM Task")
    public LiveData<List<Task>> getAllTasks();

    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

}
