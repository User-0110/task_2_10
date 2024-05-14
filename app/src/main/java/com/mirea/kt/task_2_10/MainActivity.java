package com.mirea.kt.task_2_10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btStart);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddCarActivity.class);
            startActivity(intent);
        });
    }
}