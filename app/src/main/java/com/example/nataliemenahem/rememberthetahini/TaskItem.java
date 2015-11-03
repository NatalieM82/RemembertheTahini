package com.example.nataliemenahem.rememberthetahini;

import android.renderscript.RenderScript;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NatalieMenahem on 02/11/15.
 */
public class TaskItem {

    private long taskId;
    private String description;

    public TaskItem(int id,String description) {
        super();
        setTaskId(id);
        setDescription(description);
    }

    public TaskItem() {
        super();
    }

    public TaskItem(String description) {
        super();
        setDescription(description);
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }
}
