package com.example.task10;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.task10.databinding.FragmentFirstBinding;


public class FirstFragment extends Fragment {

    FragmentFirstBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPreferences",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        binding.btnWriteName.setOnClickListener(view -> {
            if (binding.etName.getText().toString().isEmpty())
                Toast.makeText(container.getContext(), "Вы не ввели имя", Toast.LENGTH_SHORT).show();
            else{
                editor.putString("name", binding.etName.getText().toString());
                editor.apply();
                Toast.makeText(container.getContext(), "Данные записаны", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnGetName.setOnClickListener(view -> {
            String name = sharedPreferences.getString("name", "defaultName");
            binding.tvName.setText(name);

        });

        binding.btnDeleteName.setOnClickListener(view -> {
            editor.remove("name");
            editor.apply();
            Toast.makeText(container.getContext(), "Данные удалены", Toast.LENGTH_SHORT).show();

        });

        binding.btnEditName.setOnClickListener(view -> {
            if (binding.etName.getText().toString().isEmpty())
                Toast.makeText(container.getContext(), "Вы не ввели имя", Toast.LENGTH_SHORT).show();
            else{
                editor.putString("name", binding.etName.getText().toString());
                editor.apply();
                Toast.makeText(container.getContext(), "Данные изменены", Toast.LENGTH_SHORT).show();
            }
        });


        binding.btnGoToSecondFragment.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,
                    SecondFragment.class, null).commit();
        });





        return binding.getRoot();
    }
}