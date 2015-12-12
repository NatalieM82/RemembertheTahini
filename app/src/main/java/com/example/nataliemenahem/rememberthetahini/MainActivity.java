package com.example.nataliemenahem.rememberthetahini;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDataSourceChangeListener {

    private ITasksController controller;
    private static final String TAG = "MainActivity";
    private TaskItemBaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        controller = new TasksController();

        RecyclerView  lv = (RecyclerView) findViewById(R.id.listView);

//        if(lv!=null)
//        {
//            TaskItemBaseAdapter adapter = new TaskItemBaseAdapter(this, controller.GetTasks());
//            lv.setAdapter(adapter);
//            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view,
//                                        int position, long id) {
//                    Toast.makeText(MainActivity.this, "Item number " + (position + 1) + " was clicked", Toast.LENGTH_LONG).show();
//                }
//            });
//        }

        lv.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        lv.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        adapter = new TaskItemBaseAdapter(controller.GetTasks());
        lv.setAdapter(adapter);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


    /** Called when the user clicks the Plus button */
    public void createNewTask(View view) {
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivityForResult(intent, 2);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Log.v(TAG, "im here");
        if (requestCode == 2){
            if (data != null){
                String task_message = data.getStringExtra("MESSAGE");
                Toast.makeText(MainActivity.this, task_message, Toast.LENGTH_LONG).show();
                TaskItem new_task = new TaskItem(task_message);
                controller.addTask(new_task);
            }
        }
    }

    @Override
    public void DataSourceChanged() {
        if (adapter != null) {
            adapter.UpdateDataSource(controller.GetTasks());
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");

        RecyclerView  lv = (RecyclerView) findViewById(R.id.listView);
        lv.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        lv.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        adapter = new TaskItemBaseAdapter(controller.GetTasks());
        lv.setAdapter(adapter);
    }
}
