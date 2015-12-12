package com.example.nataliemenahem.rememberthetahini;

import java.util.List;

/**
 * Created by NatalieMenahem on 03/11/15.
 */
public interface IDataAcces {
    List<TaskItem> GetTask();
    void addTask(TaskItem friend);
}
