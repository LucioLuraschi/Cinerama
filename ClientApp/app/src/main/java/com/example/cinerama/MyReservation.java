package com.example.cinerama;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinerama.UnitClass.Ticket;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MyReservation extends AppCompatActivity {

    ArrayList<Ticket> reservations = new ArrayList<>();
    String ticketIDselected = "LbNbQEVM1Y6YwhNqgPW8";
    List<String> ticketListString = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_reservation_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Getting the reservations
        File ticketPath = this.getDir("tickets", Context.MODE_PRIVATE);
        if (ticketPath.isDirectory()) {
            if (ticketPath.listFiles().length == 0) {
                // No ticket detected in the folder ==> display the message about it
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("You have no reservation in this phone.\n" +
                                "Please make one in this phone to have access here")
                        .setPositiveButton("Back to menu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(builder.getContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                Intent intent = new Intent(builder.getContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                Intent intent = new Intent(builder.getContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                // There are tickets in there ==> we display them
                /*
                for (File ticketFile : ticketPath.listFiles()) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(ticketFile));
                        String ticketContent = "";
                        String ticketLine = reader.readLine();
                        while (ticketLine != null) {
                            ticketContent += ticketLine;
                            ticketLine = reader.readLine();
                            Log.i("ticketLine", ticketLine);
                        }
                        Log.i("ticketData", ticketContent);
                        JSONObject ticketData = (JSONObject) JSONValue.parse(ticketContent);
                        String ticketID = ticketFile.getName().replace(".json", "");
                        String movieName = ticketData.getString("filmTitle");
                        String sessionTime = ticketData.getString("sessionTime");
                        String place = ticketData.getString("place");
                        Ticket ticket = new Ticket(ticketID, movieName, sessionTime, place);
                        this.reservations.add(ticket);
                    } catch (IOException | JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                // putting the tickets in the selection
                ListView lv = findViewById(R.id.reservation);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ticketIDselected = reservations.get(position).getIdTicket();
                    }
                });

                for (Ticket ticket: this.reservations) {
                    ticketListString.add(ticket.getReservationString());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,ticketListString);
                lv.setAdapter(adapter);
                */
            }
        } else {
            // Displaying the message that there is no tickets (as there is no directory)
            // No ticket detected in the folder ==> display the message about it
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("An error happened during the process.\n" +
                            "Please contact the team if this problem persists")
                    .setPositiveButton("Back to menu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(builder.getContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            Intent intent = new Intent(builder.getContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            Intent intent = new Intent(builder.getContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }



        Button button = (Button) findViewById(R.id.button7);
        Intent intent = new Intent(this, MyQRCode.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("ticketID", ticketIDselected);
                startActivity(intent);
            }
        });

    }

}