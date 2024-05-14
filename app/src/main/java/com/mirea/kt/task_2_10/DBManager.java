package com.mirea.kt.task_2_10;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBManager {
    private SQLiteOpenHelper sqLiteOpenHelper;

    public DBManager(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public boolean saveCarToDatabase(Car car) {
        SQLiteDatabase database = sqLiteOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("model", car.getModel());
        values.put("number", car.getNumber());
        values.put("year", car.getYear());

        long rowId = database.insert("TABLE_CARS", null, values);

        values.clear();
        database.close();

        return rowId != -1;
    }

    public ArrayList<Car> loadAllCarsFromDatabase() {
        ArrayList<Car> passwords = new ArrayList<>();
        SQLiteDatabase database = sqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = database.query("TABLE_CARS",
                null, null, null,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String model = cursor.getString(cursor.getColumnIndexOrThrow("model"));
                String number = cursor.getString(cursor.getColumnIndexOrThrow("number"));
                int year = cursor.getInt(cursor.getColumnIndexOrThrow("year"));
                passwords.add(new Car(model, number, year));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return passwords;
    }

    /*public void clearCurrentDatabase() {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + "TABLE_PASSWORD");
        db.close();
    }*/

    public void clearDatabase() {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + "TABLE_PASSWORDS");
        db.close();
    }

    public boolean deleteService(String name) {
        SQLiteDatabase database = sqLiteOpenHelper.getWritableDatabase();
        return database.delete("TABLE_PASSWORDS", "service" + "=?", new String[]{name}) > 0;
    }

}