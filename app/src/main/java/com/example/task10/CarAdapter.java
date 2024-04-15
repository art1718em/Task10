package com.example.task10;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.task10.databinding.CarItemBinding;


import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder>{



    private final List<Car> cars;


    public CarAdapter(List<Car> cars) {
        this.cars = cars;
    }



    @NonNull
    @Override
    public CarAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CarItemBinding binding = CarItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CarAdapter.CarViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.CarViewHolder holder, int position) {
        Car item = cars.get(position);
        holder.binding.tvMark.setText(item.getMark());
        holder.binding.tvModel.setText(item.getModel());
        holder.binding.tvColor.setText(item.getColor());
        holder.binding.tvNumber.setText(item.getNumber());

    }

    @Override
    public int getItemCount() {return cars.size();}

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        CarItemBinding binding;
        public CarViewHolder(@NonNull CarItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}