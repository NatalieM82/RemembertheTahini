package com.example.nataliemenahem.rememberthetahini;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NatalieMenahem on 03/11/15.
 */
public class DAO implements IDataAcces {
    private static DAO instace;
    private Context context;
    private TaskDBHelper dbHelper;
    private String[] tasksColumns = { TasksDbContract.TaskEntry._ID,
            TasksDbContract.TaskEntry.COLUMN_TASK_DESCRIPTION,
            TasksDbContract.TaskEntry.COLUMN_TASK_STATUS,
            TasksDbContract.TaskEntry.COLUMN_TASK_ALARM,
            TasksDbContract.TaskEntry.COLUMN_TASK_DATE_DAY,
            TasksDbContract.TaskEntry.COLUMN_TASK_DATE_MONTH,
            TasksDbContract.TaskEntry.COLUMN_TASK_DATE_YEAR,
            TasksDbContract.TaskEntry.COLUMN_TASK_TIME_HOUR,
            TasksDbContract.TaskEntry.COLUMN_TASK_TIME_MINUTES};

    private static final String TAG = "DAO";


    private DAO(Context context) {
        this.context = context;
        dbHelper = new TaskDBHelper(this.context);
    }

    public static DAO getInstatnce(Context context) {
        if (instace == null)
            instace = new DAO(context);
        return instace;
    }

    @Override
    public TaskItem addTask(TaskItem task) {
        SQLiteDatabase database = null;
        try {
            database = dbHelper.getReadableDatabase();
            if (task == null)
                return null;
            //build the content values.
            ContentValues values = new ContentValues();
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_DESCRIPTION, task.getDescription());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_STATUS, task.getStatus());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_ALARM, task.get_alarm());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_DATE_DAY, task.get_dateDay());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_DATE_MONTH, task.get_dateMonth());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_DATE_YEAR, task.get_dateYear());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_TIME_HOUR, task.get_timeHour());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_TIME_MINUTES, task.get_timeMinute());


            //do the insert.
            long insertId = database.insert(TasksDbContract.TaskEntry.TABLE_NAME, null, values);

            //get the entity from the data base - extra validation, entity was insert properly.
            Cursor cursor = database.query(TasksDbContract.TaskEntry.TABLE_NAME, tasksColumns,
                    TasksDbContract.TaskEntry._ID + " = " + insertId, null, null, null, null);
            cursor.moveToFirst();
            //create the task object from the cursor.
            TaskItem newTask = cursorToTask(cursor);
            cursor.close();
            return newTask;
        }finally {
            if (database != null)
                database.close();
        }

    }

    /*
     * Create task object from the cursor.
     */
    private TaskItem cursorToTask(Cursor cursor) {
        TaskItem t = new TaskItem();
        t.setTaskId(cursor.getLong(cursor.getColumnIndex(TasksDbContract.TaskEntry._ID)));
        t.setDescription(cursor.getString(cursor
                .getColumnIndex(TasksDbContract.TaskEntry.COLUMN_TASK_DESCRIPTION)));
        t.changeStatus(cursor.getString(cursor
                .getColumnIndex(TasksDbContract.TaskEntry.COLUMN_TASK_STATUS)));
        t.set_alarm(cursor.getInt(cursor
                .getColumnIndex(TasksDbContract.TaskEntry.COLUMN_TASK_ALARM)));
        t.set_dateDay(cursor.getInt(cursor
                .getColumnIndex(TasksDbContract.TaskEntry.COLUMN_TASK_DATE_DAY)));
        t.set_dateMonth(cursor.getInt(cursor
                .getColumnIndex(TasksDbContract.TaskEntry.COLUMN_TASK_DATE_MONTH)));
        t.set_dateYear(cursor.getInt(cursor
                .getColumnIndex(TasksDbContract.TaskEntry.COLUMN_TASK_DATE_YEAR)));
        t.set_timeHour(cursor.getInt(cursor
                .getColumnIndex(TasksDbContract.TaskEntry.COLUMN_TASK_TIME_HOUR)));
        t.set_timeMinute(cursor.getInt(cursor
                .getColumnIndex(TasksDbContract.TaskEntry.COLUMN_TASK_TIME_MINUTES)));
        return t;
    }

    @Override
    public void removeTask(TaskItem task) {
        SQLiteDatabase database = null;
        try {
            database = dbHelper.getReadableDatabase();
            long id = task.getTaskId();
            database.delete(TasksDbContract.TaskEntry.TABLE_NAME, TasksDbContract.TaskEntry._ID + " = " + id,
                    null);
        }finally {
            if(database != null){
                database.close();
            }
        }
    }

    @Override
    public TaskItem getTask(long task_id) {
        SQLiteDatabase database = null;
        try {
            database = dbHelper.getReadableDatabase();

            //get the entity from the data base - extra validation, entity was insert properly.
            Cursor cursor = database.query(TasksDbContract.TaskEntry.TABLE_NAME, tasksColumns,
                    TasksDbContract.TaskEntry._ID + " = " + task_id, null, null, null, null);
            cursor.moveToFirst();
            //create the task object from the cursor.
            TaskItem task = cursorToTask(cursor);
            cursor.close();
            return task;
        }finally {
            if (database != null)
                database.close();
        }
    }

    @Override
    public TaskItem editTask(TaskItem task) {
        SQLiteDatabase database = null;
        try {
            database = dbHelper.getReadableDatabase();
            if (task == null)
                return null;
            //build the content values.

            ContentValues values = new ContentValues();

            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_DESCRIPTION, task.getDescription());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_STATUS, task.getStatus());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_ALARM, task.get_alarm());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_DATE_DAY, task.get_dateDay());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_DATE_MONTH, task.get_dateMonth());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_DATE_YEAR, task.get_dateYear());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_TIME_HOUR, task.get_timeHour());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_TIME_MINUTES, task.get_timeMinute());

            long edit_task_id = task.getTaskId();

            database.update(TasksDbContract.TaskEntry.TABLE_NAME, values, "_id " + "=" + edit_task_id, null);

            //get the entity from the data base - extra validation, entity was insert properly.
            Cursor cursor = database.query(TasksDbContract.TaskEntry.TABLE_NAME, tasksColumns,
                    TasksDbContract.TaskEntry._ID + " = " + edit_task_id, null, null, null, null);
            cursor.moveToFirst();
            //create the task object from the cursor.
            TaskItem newTask = cursorToTask(cursor);
            cursor.close();
            return newTask;
        }finally {
            if (database != null)
                database.close();
        }
    }

    @Override
    public TaskItem changeStatus(TaskItem task) {
        SQLiteDatabase database = null;
        try {
            database = dbHelper.getReadableDatabase();
            if (task == null)
                return null;
            //build the content values.

//            Cursor cursor =  database.rawQuery("select * from " + dbHelper.getDatabaseName() + " where " + BanksTable.COL_NAME + "='" + bankName + "'" , null);

            ContentValues values = new ContentValues();

            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_DESCRIPTION, task.getDescription());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_STATUS, task.getStatus());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_DATE_DAY, task.get_dateDay());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_DATE_MONTH, task.get_dateMonth());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_DATE_YEAR, task.get_dateYear());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_TIME_HOUR, task.get_timeHour());
            values.put(TasksDbContract.TaskEntry.COLUMN_TASK_TIME_MINUTES, task.get_timeMinute());

            long edit_task_id = task.getTaskId();

            database.update(TasksDbContract.TaskEntry.TABLE_NAME, values, "_id " + "=" + edit_task_id, null);

            //get the entity from the data base - extra validation, entity was insert properly.
            Cursor cursor = database.query(TasksDbContract.TaskEntry.TABLE_NAME, tasksColumns,
                    TasksDbContract.TaskEntry._ID + " = " + edit_task_id, null, null, null, null);
            cursor.moveToFirst();
            //create the task object from the cursor.
            TaskItem newTask = cursorToTask(cursor);
            cursor.close();
            return newTask;
        }finally {
            if (database != null)
                database.close();
        }
    }


    @Override
    public List<TaskItem> getAllTasks() {
        SQLiteDatabase database = null;
        try {
            database = dbHelper.getReadableDatabase();
            List<TaskItem> tasks = new ArrayList<TaskItem>();

            Cursor cursor = database.query(TasksDbContract.TaskEntry.TABLE_NAME, tasksColumns,
                    null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                TaskItem f = cursorToTask(cursor);
                tasks.add(f);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
            return tasks;
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }

}
