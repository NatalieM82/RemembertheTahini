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

    public int _dateYear;
    public int _dateMonth;
    public int _dateDay;
    public int _timeHour;
    public int _timeMinute;
    public int _alarm;

    private static final String TAG = "TaskItem";


    public TaskItem(long id,String description) {
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

    public TaskItem(String taskMessage, int dateYear, int dateMonth, int dateDay, int timeHour, int timeMinute,int alarm) {
       /* alarm */
//        if ( (dateYear!=-1&&dateMonth!=-1&&dateDay!=-1) || (timeHour!=-1&&timeMinute!=-1) || (mapLongitude!=-1&&mapLatitude!=-1) )
//            _alarm=true;

        /* done */
        //do something with done

//        setTaskId(id);
        setDescription(taskMessage);

        this._dateYear = dateYear;
        this._dateMonth = dateMonth;
        this._dateDay = dateDay;

        this._timeHour = timeHour;
        this._timeMinute = timeMinute;

        this._alarm = alarm;
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

    // set/get Alarm
    public int get_alarm() {
        return this._alarm;
    }

    public void set_alarm(int alarm) {
        this._alarm = alarm;
    }

    public int get_dateYear() {
        return _dateYear;
    }

    public void set_dateYear(int _dateYear) {
        this._dateYear = _dateYear;
    }

    public int get_dateDay() {
        return _dateDay;
    }

    public void set_dateDay(int _dateDay) {
        this._dateDay = _dateDay;
    }

    public int get_dateMonth() {
        return _dateMonth;
    }

    public void set_dateMonth(int _dateMonth) {
        this._dateMonth = _dateMonth;
    }

    public int get_timeHour() {
        return _timeHour;
    }

    public void set_timeHour(int _timeHour) {
        this._timeHour = _timeHour;
    }

    public int get_timeMinute() {
        return _timeMinute;
    }

    public void set_timeMinute(int _timeMinute) {
        this._timeMinute = _timeMinute;
    }

}
