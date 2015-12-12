package com.example.nataliemenahem.rememberthetahini;

import android.renderscript.RenderScript;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NatalieMenahem on 02/11/15.
 */
public class TaskItem {

    private long taskId;
    private String description;
    private String status;

    private static final String TAG = "TaskItem";


    public TaskItem(int id,String description) {
        super();
        setTaskId(id);
        setDescription(description);
        status = "false";
    }

    public TaskItem() {
        super();
        status = "false";
    }

    public TaskItem(String description) {
        super();
        setDescription(description);
        status = "false";
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

    public void changeStatus(String status) {
//        Log.i(TAG, "Change status: "+status);
        this.status = status;
    }

    public String getStatus() {return status;}
}
