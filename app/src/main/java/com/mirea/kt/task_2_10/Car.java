package com.mirea.kt.task_2_10;

public class Car {

    private String model;
    private String number;
    private int year;

    public Car(String model, String number, int year) {
        this.model = model;
        this.number = number;
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public int getYear() {
        return year;
    }
}