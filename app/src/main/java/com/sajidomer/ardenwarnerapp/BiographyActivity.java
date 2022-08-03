package com.sajidomer.ardenwarnerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sajidomer.ardenwarnerapp.databinding.ActivityBiographyBinding;

public class BiographyActivity extends AppCompatActivity {

    private ActivityBiographyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBiographyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}