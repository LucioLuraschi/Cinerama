package com.example.cinerama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        Intent intent = new Intent(this, SeeMovieList.class);
        Intent intentFromResa = getIntent();
        if (intentFromResa.getStringExtra("messageResa") != null) {
            Toast.makeText(this,
                    intentFromResa.getStringExtra("messageResa"), Toast.LENGTH_SHORT)
                .show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        button = (Button) findViewById(R.id.button2);
        Intent intent2 = new Intent(this, MyReservation.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });

        Log.i("Directory Info", "Path : " + this.getFilesDir().getPath());

        // Création du dossier des tickets s'il n'existe pas
        this.getDir("tickets", Context.MODE_PRIVATE);

    }
}