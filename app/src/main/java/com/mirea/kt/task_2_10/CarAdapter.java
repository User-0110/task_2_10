package com.mirea.kt.task_2_10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    private ArrayList<Car> cars;

    public CarAdapter(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView model;
        private final TextView number;
        private final TextView year;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            model = itemView.findViewById(R.id.model);
            number = itemView.findViewById(R.id.number);
            year = itemView.findViewById(R.id.year);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.model.setText(String.format("%s", car.getModel()));
        holder.number.setText(String.format("%s", car.getNumber()));
        holder.year.setText(String.format("%s", car.getYear()));
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
}