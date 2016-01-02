package com.example.nataliemenahem.rememberthetahini;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NatalieMenahem on 12/12/2015.
 */
public class TaskDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 6;

    private static final String DATABASE_NAME = "tasks.db";

    public TaskDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a table to hold the friends;
        final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE "
                + TasksDbContract.TaskEntry.TABLE_NAME + " (" + TasksDbContract.TaskEntry._ID
                + " INTEGER PRIMARY KEY," + TasksDbContract.TaskEntry.COLUMN_TASK_DESCRIPTION
                + " TEXT NOT NULL, "
                + TasksDbContract.TaskEntry.COLUMN_TASK_STATUS + " TEXT NOT NULL, "
                + TasksDbContract.TaskEntry.COLUMN_TASK_ALARM + " INTEGER, "
                + TasksDbContract.TaskEntry.COLUMN_TASK_DATE_DAY + " INTEGER, "
                + TasksDbContract.TaskEntry.COLUMN_TASK_DATE_MONTH + " INTEGER, "
                + TasksDbContract.TaskEntry.COLUMN_TASK_DATE_YEAR + " INTEGER, "
                + TasksDbContract.TaskEntry.COLUMN_TASK_TIME_HOUR + " INTEGER, "
                + TasksDbContract.TaskEntry.COLUMN_TASK_TIME_MINUTES +" INTEGER )";
        db.execSQL(SQL_CREATE_LOCATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TasksDbContract.TaskEntry.TABLE_NAME);
        onCreate(db);

    }
}
