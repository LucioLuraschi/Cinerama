package com.example.cinerama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.FirstPartyScopes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConfirmReservation extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_reservation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentFromSeatsReservation = getIntent();
        ArrayList<String> seatsTaken = intentFromSeatsReservation.getStringArrayListExtra("Taken seats");
        String sessionID = intentFromSeatsReservation.getStringExtra("SessionID");
        String filmTitle = intentFromSeatsReservation.getStringExtra("filmTitle");
        String sessionTimeString = intentFromSeatsReservation.getStringExtra("sessionTime");

        // Putting the information
        TextView movieTile = (TextView) findViewById(R.id.movieTitleResa);
        movieTile.setText(filmTitle);
        TextView sessionTime = (TextView) findViewById(R.id.sessionTimeResa);
        sessionTime.setText(sessionTimeString);
        TextView numberSeatsTaken = (TextView) findViewById(R.id.numberSeatTakenResa);
        numberSeatsTaken.setText("" + seatsTaken.size());

        // Event avec la validation
        Button resaButton = (Button) findViewById(R.id.resaButton);
        EditText firstNameText = (EditText) findViewById(R.id.FirstNameClient);
        EditText lastNameText = (EditText) findViewById(R.id.LastNameClient);
        resaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameText.getText().toString();
                String lastName = lastNameText.getText().toString();
                if (firstName.equals("First Name") || lastName.equals("Last Name")
                        || firstName.length() == 0 || lastName.length() == 0) {

                    return;
                }
                int i = 0;
                for (String seatID: seatsTaken) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("nom", lastName);
                    data.put("prenom", firstName);
                    data.put("id_session", sessionID);
                    data.put("place", seatID);
                    FirebaseFirestore.getInstance().collection("ticket")
                            .add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(resaButton.getContext(),
                                            "Reservation réussie", Toast.LENGTH_SHORT);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(resaButton.getContext(),"Fail de la réservaltion", Toast.LENGTH_LONG);
                                }
                            });
                    Intent intentToSelection = new Intent(resaButton.getContext(), SeeMovieList.class);

                }

            }
        });

    }
}