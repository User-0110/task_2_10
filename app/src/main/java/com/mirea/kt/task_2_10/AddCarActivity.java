package com.mirea.kt.task_2_10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Добавить автомобиль");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        DBManager manager = new DBManager(new MyAppSQLiteHelper(this, "cars.db", null, 1));

        Button addCars = findViewById(R.id.addCar);
        Button showCars = findViewById(R.id.showCars);

        EditText tvModel = findViewById(R.id.model);
        EditText tvNumber = findViewById(R.id.number);
        EditText tvYear = findViewById(R.id.year);

        addCars.setOnClickListener(v -> {
            String model = tvModel.getText().toString();
            String number = tvNumber.getText().toString();
            String year = tvYear.getText().toString();
            if (!model.isEmpty() && !number.isEmpty() && !year.isEmpty()) {
                boolean result = manager.saveCarToDatabase(new Car(model, number, Integer.parseInt(year)));
                if (result) {
                    Toast.makeText(getApplicationContext(), "Запись успешно добавлена!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Произошла ошибка при добавлении записи", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Заполните все поля!", Toast.LENGTH_LONG).show();
            }
        });
        showCars.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CarsListActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("menu_create", "Создано меню");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.simple_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Log.d("menu_button_click", "Нажата кнопка домой");
            finish();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}