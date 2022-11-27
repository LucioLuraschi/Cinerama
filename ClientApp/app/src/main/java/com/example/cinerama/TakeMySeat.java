package com.example.cinerama;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class TakeMySeat extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_my_seat_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton imageButton = (ImageButton) findViewById(R.id.seat1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable.ConstantState imgId1 = imageButton.getDrawable().getConstantState();
                Drawable.ConstantState imgId2 = getDrawable(R.drawable.seat_no_available).getConstantState();
                if(imgId1!=imgId2) {
                    imageButton.setImageResource(R.drawable.seat_no_available);
                }else {
                    imageButton.setImageResource(R.drawable.seat_available);
                }
                /*if (imageButton.getResources().equals(R.drawable.seat_available)){
                    imageButton.setImageResource(R.drawable.seat_no_available);
                }
                if (imageButton.getResources().equals(R.drawable.seat_no_available)){
                    imageButton.setImageResource(R.drawable.seat_available);
                }*/
            }
        });

        ImageButton imageButton2 = (ImageButton) findViewById(R.id.seat2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable.ConstantState imgId1 = imageButton2.getDrawable().getConstantState();
                Drawable.ConstantState imgId2 = getDrawable(R.drawable.seat_no_available).getConstantState();
                if(imgId1!=imgId2) {
                    imageButton2.setImageResource(R.drawable.seat_no_available);
                }else {
                    imageButton2.setImageResource(R.drawable.seat_available);
                }
            }
        });

        ImageButton imageButton3 = (ImageButton) findViewById(R.id.seat3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable.ConstantState imgId1 = imageButton3.getDrawable().getConstantState();
                Drawable.ConstantState imgId2 = getDrawable(R.drawable.seat_no_available).getConstantState();
                if(imgId1!=imgId2) {
                    imageButton3.setImageResource(R.drawable.seat_no_available);
                }else {
                    imageButton3.setImageResource(R.drawable.seat_available);
                }
            }
        });

        ImageButton imageButton4 = (ImageButton) findViewById(R.id.seat4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable.ConstantState imgId1 = imageButton4.getDrawable().getConstantState();
                Drawable.ConstantState imgId2 = getDrawable(R.drawable.seat_no_available).getConstantState();
                if(imgId1!=imgId2) {
                    imageButton4.setImageResource(R.drawable.seat_no_available);
                }else {
                    imageButton4.setImageResource(R.drawable.seat_available);
                }
            }
        });


    }

}