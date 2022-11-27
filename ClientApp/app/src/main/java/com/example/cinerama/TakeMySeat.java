package com.example.cinerama;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class TakeMySeat extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_my_seat_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

}