package com.example.nataliemenahem.rememberthetahini;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nataliemenahem.rememberthetahini.R;

public class NewTaskActivity extends Activity {

    private EditText descEt;
    public long edit_task_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        descEt = (EditText) findViewById(R.id.editText);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            String taskDesc = extras.getString(AppConst.ExtrasTaskName);
            edit_task_id = extras.getLong(AppConst.ExtrasTaskId);

            if (taskDesc != null)
            {
                descEt.setText(taskDesc);
            }

        }
    }

    public void okClicked(View v)
    {
        if(descEt == null) return;
        String name = descEt.getText().toString();
        // Prepare data intent
        Intent data = new Intent();
        //Put the extras.
        data.putExtra(AppConst.ExtrasTaskName, name);

        if (edit_task_id != 0)
            data.putExtra(AppConst.ExtrasTaskId, edit_task_id);
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        finish();
    }
}
