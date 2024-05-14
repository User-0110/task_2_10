package com.mirea.kt.task_2_10;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cars);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Список автомобилей");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        DBManager manager = new DBManager(new MyAppSQLiteHelper(this, "cars.db", null, 1));
        ArrayList<Car> cars = manager.loadAllCarsFromDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        CarAdapter adapter = new CarAdapter(cars);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
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
        else if (item.getItemId() == R.id.action_refresh) {
            Log.d("menu_button_click", "Нажата кнопка обновить страницу");
            finish();
            startActivity(getIntent());
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}