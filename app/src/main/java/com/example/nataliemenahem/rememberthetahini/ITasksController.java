package com.example.nataliemenahem.rememberthetahini;

import java.util.List;

/**
 * Created by NatalieMenahem on 03/11/15.
 */
public interface ITasksController {
    List<TaskItem> GetTasks();
    void addTask(TaskItem task);
}
