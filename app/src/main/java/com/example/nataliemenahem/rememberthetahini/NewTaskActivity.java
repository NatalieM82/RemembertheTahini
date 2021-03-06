package com.example.nataliemenahem.rememberthetahini;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nataliemenahem.rememberthetahini.R;

import java.util.Calendar;
import java.util.Date;

public class NewTaskActivity extends Activity implements DialogListener, OnDataSourceChangeListener {

    private EditText descEt;
    public long edit_task_id;
    private TaskItem currentTask;

    private int hasAlarm = 0; //0 no , 1 yes
    private int isDone = 0;   //0 no , 1 yes
    private int timeHour = -1;
    private int timeMinute = -1;
    private int dateYear = -1;
    private int dateMonth = -1;
    private int dateDay = -1;
    private boolean datechosen = false;
    private boolean timechosen = false;

    private TasksController controller;
    private TaskItemBaseAdapter adapter;

    private static final String TAG = "NewTaskActivity";


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        descEt = (EditText) findViewById(R.id.editText);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        // create the controller.
        controller = new TasksController(this);
        // register for OnDataSourceChangedListener event.
        controller.registerOnDataSourceChanged(this);

        if (extras != null) {
            String taskDesc = extras.getString(AppConst.ExtrasTaskName);
            edit_task_id = extras.getLong(AppConst.ExtrasTaskId);

            if (taskDesc != null)
            {
                descEt.setText(taskDesc);
            }

            currentTask = controller.getTask(edit_task_id);
            hasAlarm = currentTask.get_alarm();

            timeHour = currentTask.get_timeHour();
            timeMinute = currentTask.get_timeMinute();
            dateYear = currentTask.get_dateYear();
            dateMonth = currentTask.get_dateMonth();
            dateDay = currentTask.get_dateDay();

            CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_notify);
            TextView timeText = (TextView) findViewById(R.id.timeText);
            TextView dateText = (TextView) findViewById(R.id.dateText);
            if (hasAlarm == 1) {
                checkBox.setChecked(true);

                timeText.setText(timeHour + ":" + timeMinute);


                dateText.setText(dateDay + "/" + (dateMonth+1) + "/" + dateYear);
            }
            else {
                timeText.setVisibility(View.GONE);
                dateText.setVisibility(View.GONE);

            }


        }



    }

    @Override
    public void DataSourceChanged() {
        if (adapter != null) {
            adapter.UpdateDataSource(controller.getAllTasks());
            adapter.notifyDataSetChanged();
        }

    }


    public void okClicked(View v) {
        if (descEt == null) return;
        String name = descEt.getText().toString();
        // Prepare data intent
        Intent data = new Intent();

        //Creating the task
        TaskItem newTask=new TaskItem(name, dateYear, dateMonth, dateDay, timeHour, timeMinute,hasAlarm);

        //Two cases: 1. new task 2. edit task

        long latestId;


        if (edit_task_id != 0) {
            newTask.setTaskId(edit_task_id);
            newTask.changeStatus("false");
            latestId = controller.editTask(newTask);
            newTask.setTaskId(latestId);
        }
        else
        {
            newTask.changeStatus("false");
            latestId = controller.addTask(newTask);
            newTask.setTaskId(latestId);
        }


        //Checking if alarm is needed
        if (hasAlarm == 1) {
            if ((timeHour != -1 && timeMinute != -1) || (dateDay != -1 && dateMonth != -1 && dateYear != -1)) {
                System.out.println("$$$$$$$$$$$$");
                System.out.println("setting alarm");
                newTask.set_alarm(hasAlarm);
                createAlarmAtDate(newTask);
            }
        }


        // Activity finished ok, return the data
        setResult(RESULT_OK, data);

        initialize_variables();
        finish();

    }

    public void initialize_variables() {
        try {
        /* initialize variables */
            // Time
            timeHour = -1;
            timeMinute = -1;
            // Date
            dateYear = -1;
            dateMonth = -1;
            dateDay = -1;

            hasAlarm = 0;
            isDone = 0;

            TextView timeText = (TextView) findViewById(R.id.timeText);
            TextView dateText = (TextView) findViewById(R.id.dateText);

            timeText.setText("");
            dateText.setText("");
            timeText.setVisibility(View.GONE);
            dateText.setVisibility(View.GONE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createAlarmAtDate(TaskItem task) {
        try {
            Intent intent = new Intent();
            intent.setAction("com.example.nataliemenahem.rememberthetahini.ReminderBroadCastReceiver");
            intent.putExtra("taskMessage", task.getDescription());
            intent.putExtra("taskId", task.getTaskId());

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int) task.getTaskId(), intent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + millisecondsUntilDate(), pendingIntent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long millisecondsUntilDate() {
        try {

            Calendar cal = Calendar.getInstance();
            //Only time set
            if (timeHour != -1 && timeMinute != -1 && dateDay == -1 && dateMonth == -1 && dateYear == -1) {
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), timeHour, timeMinute);
            }

            //Date & time set
            if (dateDay != -1 && dateMonth != -1 && dateYear != -1 && timeHour != -1 && timeMinute != -1) {
                cal.set(dateYear, dateMonth, dateDay, timeHour, timeMinute, 0);
            }

            //Only date set
            if (dateDay != -1 && dateMonth != -1 && dateYear != -1 && timeHour == -1 && timeMinute == -1) {
                cal.set(dateYear, dateMonth, dateDay, 10, 0, 0);
            }

            Calendar now = Calendar.getInstance();
            long diff_in_ms = cal.getTimeInMillis() - now.getTimeInMillis();
            return diff_in_ms;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void cancel(View view)
    {
        initialize_variables();
        finish();
    }

    public void showTimePickerDialog(View v) {
        timechosen = true;
        try {
            DialogFragment newFragment = new TimePickerFragment();

            // add details if user want to edit exist task
            if (timeHour != 0 && timeMinute != 0) {
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("timeHour", timeHour);
                dataBundle.putInt("timeMinute", timeMinute);
                newFragment.setArguments(dataBundle);
            }
            newFragment.show(getFragmentManager(), "timePicker");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDatePickerDialog(View v) {
        datechosen = true;
        try {
            DialogFragment newFragment = new DatePickerFragment();

            // add details if user want to edit exist task
            if (dateYear != 0 && dateMonth != 0 && dateDay != 0) {
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("dateYear", dateYear);
                dataBundle.putInt("dateMonth", dateMonth);
                dataBundle.putInt("dateDay", dateDay);
                newFragment.setArguments(dataBundle);
                System.out.println("save bundle----------" + dateDay + "." + dateMonth + "." + dateYear);
            }
            newFragment.show(getFragmentManager(), "datePicker");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        ImageButton bTime = (ImageButton) findViewById(R.id.time);
        ImageButton bDate = (ImageButton) findViewById(R.id.date);
        if (checked) {
            bTime.setEnabled(true);
            bDate.setEnabled(true);
            hasAlarm = 1;

        }
        else {
            bTime.setEnabled(false);
            bDate.setEnabled(false);
            hasAlarm = 0;
        }
    }

    @Override
    public void onFinishEditDialog(Intent data) {
        final Calendar c = Calendar.getInstance();
        try {
        /* For TimePicker Fragment */
            if (data.getExtras().containsKey("hour"))
                timeHour = data.getExtras().getInt("hour");
            if (data.getExtras().containsKey("minute"))
                timeMinute = data.getExtras().getInt("minute");

        /* For DatePicker Fragment */
            if (data.getExtras().containsKey("year"))
                dateYear = data.getExtras().getInt("year");
            if (data.getExtras().containsKey("month"))
                dateMonth = data.getExtras().getInt("month");
            if (data.getExtras().containsKey("day"))
                dateDay = data.getExtras().getInt("day");

            if (datechosen == false) {
                dateYear = c.get(Calendar.YEAR);
                dateMonth = c.get(Calendar.MONTH);
                dateDay = c.get(Calendar.DAY_OF_MONTH);
            }

            if (timechosen == false) {
                timeHour = c.get(Calendar.HOUR_OF_DAY);
                timeMinute = c.get(Calendar.MINUTE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
