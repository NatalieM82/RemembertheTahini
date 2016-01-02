package com.example.nataliemenahem.rememberthetahini;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NatalieMenahem on 03/11/15.
 */
public class TasksController {

    private List<String> descriptionList;
    private Context context;
    private IDataAcces dao;

    private static final String TAG = "TasksController";

    private List<OnDataSourceChangeListener> dataSourceChangedListenrs = new ArrayList<OnDataSourceChangeListener>();

    TasksController(Context context) {
        this.context = context;
        dao = DAO.getInstatnce(context.getApplicationContext());
    }

    public List<TaskItem> getAllTasks() {
        try {
            //TODO In some cases cache the data is the best practice.
            List<TaskItem> fl = dao.getAllTasks();
            return fl;
        } catch (Exception e) {
            // in case of error, return empty list.
            return new ArrayList<TaskItem>();
        }
    }

    public long addTask(TaskItem new_task){
        try {
            TaskItem retTask = dao.addTask(new_task);
            if(retTask == null) return 0;
            //update what ever it will be.
            invokeDataSourceChanged();

            return retTask.getTaskId();
        } catch (Exception e) {
            Log.e("MainController",e.getMessage());
        }

        return 1;
    }

    public long editTask(TaskItem new_task){
        try {
            TaskItem retTask = dao.editTask(new_task);
            if(retTask == null) return 0;
            //update what ever it will be.
            invokeDataSourceChanged();
            return retTask.getTaskId();
        } catch (Exception e) {
            Log.e("MainController",e.getMessage());
        }

        return 1;
    }

    public TaskItem getTask(long task_id){
        TaskItem retTask = dao.getTask(task_id);
        //update what ever it will be.
        invokeDataSourceChanged();
        return retTask;
    }

    public void changeStatus(TaskItem new_task){
        try {
//            Log.i(TAG, new_task.getStatus());
            TaskItem retTask = dao.changeStatus(new_task);
            if(retTask == null) return;
            //update what ever it will be.
            invokeDataSourceChanged();
        } catch (Exception e) {
            Log.e("MainController",e.getMessage());
        }
    }

    public void removeTask(TaskItem task) {
        //remove the friend from the database.
        dao.removeTask(task);
        invokeDataSourceChanged();
    }

    public void registerOnDataSourceChanged(OnDataSourceChangeListener listener)
    {
        if(listener!=null)
            dataSourceChangedListenrs.add(listener);
    }
    public void unRegisterOnDataSourceChanged(OnDataSourceChangeListener listener)
    {
        if(listener!=null)
            dataSourceChangedListenrs.remove(listener);
    }

    public void invokeDataSourceChanged()
    {
        for (OnDataSourceChangeListener listener : dataSourceChangedListenrs) {
            listener.DataSourceChanged();
        }
    }

}
