package com.example.scanmyseat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;

import com.google.common.util.concurrent.ListenableFuture;

public class ResumeQrCode extends AppCompatActivity {

    private TextView movieName;
    private TextView releaseYear;
    private TextView room;
    private TextView dateShow;
    private TextView hourShow;
    private TextView isValidOrNot;
    private ImageView imageView;
    private String numberTicket;
    private TableRow tableRow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_qr_code);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        movieName = findViewById(R.id.movieName);
        room = findViewById(R.id.room);
        dateShow = findViewById(R.id.dateShow);
        hourShow = findViewById(R.id.hourShow);
        isValidOrNot = findViewById(R.id.isValidOrNot);
        imageView = findViewById(R.id.imageView2);
        tableRow =  findViewById(R.id.tableRow);

        //get number of the ticket from the qrcode scan
        numberTicket = getIntent().getStringExtra("ticket");

        //Connect to the api to get all informations for the number of the ticket

        //display the informations of the ticket
        movieName.setText(getIntent().getStringExtra("ticket"));
        room.setText("Room n ° ");
        dateShow.setText("Date Show");
        hourShow.setText("Hour Show");
        isValidOrNot.setText("Valid ?");


        //check if it is available with the date and hour (access to the cinema 2h hour before the movie
        //until 30 min after the beginning of the movie
        /*
        Conversion des dates pour comparaison
        if (dateshow is today){
            if(hourshow - 2 <= hourshow and hourshow <= hourshow + 1){
                isValidOrNot.setText("Valid");
                imageView.setImageResource(R.drawable.valid);
                tableRow.setBackgroundResource(R.drawable.row_shape_valid);
            } else {
                isValidOrNot.setText("No Valid");
                imageView.setImageResource(R.drawable.novalid);
                tableRow.setBackgroundResource(R.drawable.row_shape_novalid);
            }
        }
         */
    }




}
