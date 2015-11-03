package com.example.nataliemenahem.rememberthetahini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
    }

    public void cancelBTN(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
