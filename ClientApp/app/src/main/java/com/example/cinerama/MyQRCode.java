package com.example.cinerama;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MyQRCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_qr_code_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
