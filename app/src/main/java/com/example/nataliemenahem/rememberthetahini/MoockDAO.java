package com.example.nataliemenahem.rememberthetahini;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by NatalieMenahem on 03/11/15.
 */
public class MoockDAO implements IDataAcces {
    private List<String> descriptionList;

    private static MoockDAO instance;

    private MoockDAO() {
        descriptionList = new ArrayList<String>();
        descriptionList.add("Task #1");
        descriptionList.add("Task #2");
        descriptionList.add("Task #3");
        descriptionList.add("Task #4");
        descriptionList.add("Task #5");
        descriptionList.add("Task #6");
        descriptionList.add("Task #7");
        descriptionList.add("Task #8");
        descriptionList.add("Task #9");
        descriptionList.add("Task #10");
        descriptionList.add("Task #11");
        descriptionList.add("Task #12");
        descriptionList.add("Task #13");
        descriptionList.add("Task #14");
        descriptionList.add("Task #15");
        descriptionList.add("Task #16");
        descriptionList.add("Task #17");
        descriptionList.add("Task #18");
        descriptionList.add("Task #19");
        descriptionList.add("Task #20");
    }

    /*
     * Single tone implement.
     */
    public static MoockDAO getInstance()
    {
        if(instance ==  null)
            instance = new MoockDAO();
        return instance;
    }

    public List<TaskItem> GetTask() {
        return GenerateRandomTask();
    }

    private List<TaskItem> GenerateRandomTask() {
        List<TaskItem> taskList = new ArrayList<TaskItem>();
        for (int i = 0; i < 20; i++) {
            TaskItem item = new TaskItem(descriptionList.get(i));
            taskList.add(item);
        }
        return taskList;
    }
}
