package com.example.task10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.task10.databinding.FragmentSecondBinding;

import java.util.List;

public class SecondFragment extends Fragment {

    FragmentSecondBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        DatabaseHelper dbHelper = new DatabaseHelper(container.getContext());
        List<Car> cars = dbHelper.getAllCars();
        CarAdapter adapter = new CarAdapter(cars);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        binding.recyclerView.setAdapter(adapter);

        binding.btnSave.setOnClickListener(view -> {

            if (dbHelper.addCar(new Car(
                    binding.etCarNumber.getText().toString(),
                    binding.etCarMark.getText().toString(),
                    binding.etCarModel.getText().toString(),
                    binding.etCarColor.getText().toString()
            )))
            {
                cars.add(new Car(
                        binding.etCarNumber.getText().toString(),
                        binding.etCarMark.getText().toString(),
                        binding.etCarModel.getText().toString(),
                        binding.etCarColor.getText().toString()
                ));
                refreshContactsList(dbHelper, cars, adapter);
                Toast.makeText(container.getContext(),
                        "Машина успешно добавлена!", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(container.getContext(),
                        "Ошибка при добавлении машины!", Toast.LENGTH_SHORT).show();
        });


        binding.btnDelete.setOnClickListener(view -> {
            if (dbHelper.deleteCar(binding.etCarNumber.getText().toString())) {
                int position = -1;
                for (int i = 0; i < cars.size(); i++) {
                    if (cars.get(i).getNumber().equals(binding.etCarNumber.getText().toString()))
                    {
                        position = i;
                        cars.remove(i);
                        break;
                    }
                }
                if (position != -1) {
                    refreshContactsList(dbHelper, cars, adapter);
                    Toast.makeText(container.getContext(),
                            "Машина успешно удалена!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(container.getContext(), "Машина не найдена", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(container.getContext(), "Ошибка при удалении машины", Toast.LENGTH_SHORT).show();
        });

        binding.btnFind.setOnClickListener(view -> {
            Car foundCar = dbHelper.findContact(binding.etCarNumber.getText().toString());
            if (foundCar != null) {
                Toast.makeText(
                        container.getContext(),
                        foundCar.getMark() + " " + foundCar.getModel() + " " + foundCar.getColor() +
                                " " + foundCar.getNumber(),
                        Toast.LENGTH_SHORT
                ).show();
            } else
                Toast.makeText(container.getContext(), "Машина не найдена", Toast.LENGTH_SHORT).show();
        });

        binding.btnUpdate.setOnClickListener(view -> {
            if (dbHelper.updateCar(
                    binding.etCarNumber.getText().toString(),
                    binding.etCarNumber.getText().toString(),
                    binding.etCarMark.getText().toString(),
                    binding.etCarModel.getText().toString(),
                    binding.etCarColor.getText().toString()
            )) {
                Toast.makeText(container.getContext(),
                        "Машина была успешно обновлена!", Toast.LENGTH_SHORT).show();
                refreshContactsList(dbHelper, cars, adapter);
            } else
                Toast.makeText(container.getContext(),
                        "Ошибка при обновлении машины", Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }

    private void refreshContactsList(DatabaseHelper dbHelper, List<Car> cars, CarAdapter adapter) {
        cars = dbHelper.getAllCars();
        adapter = new CarAdapter(cars);
        binding.recyclerView.setAdapter(adapter);
    }

}