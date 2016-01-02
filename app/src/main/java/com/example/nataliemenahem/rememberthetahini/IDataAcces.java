package com.example.nataliemenahem.rememberthetahini;

import com.example.nataliemenahem.rememberthetahini.TaskItem;

import java.util.List;

/**
 * Created by NatalieMenahem on 03/11/15.
 */
public interface IDataAcces {
    List<TaskItem> getAllTasks();
    TaskItem addTask(TaskItem task);
    TaskItem getTask(long task_id);
    void removeTask(TaskItem task);
    TaskItem editTask(TaskItem task);

    TaskItem changeStatus(TaskItem task);

}
