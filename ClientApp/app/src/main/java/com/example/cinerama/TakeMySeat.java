package com.example.cinerama;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinerama.UnitClass.Seat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TakeMySeat extends AppCompatActivity {

    ArrayList<String> selectedSeat = new ArrayList<>();
    ArrayList<Seat> seats = new ArrayList<>();

    private int[][] androidIDSeats = {
            {R.id.seatA1, R.id.seatA2, R.id.seatA3, R.id.seatA4},
            {R.id.seatB1, R.id.seatB2, R.id.seatB3, R.id.seatB4},
            {R.id.seatC1, R.id.seatC2, R.id.seatC3, R.id.seatC4},
            {R.id.seatD1, R.id.seatD2, R.id.seatD3, R.id.seatD4},
            {R.id.seatE1, R.id.seatE2, R.id.seatE3, R.id.seatE4},
            {R.id.seatF1, R.id.seatF2, R.id.seatF3, R.id.seatF4},
            {R.id.seatG1, R.id.seatG2, R.id.seatG3, R.id.seatG4},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_my_seat_activity);

        Button reservationButton = (Button) findViewById(R.id.button3);

        // Making the list of the seat
        char[] ranks = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[] columns = {1,2,3,4};
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                String placeName = "" + ranks[i] + columns[j];
                int idPlace = androidIDSeats[i][j];
                Seat seat = new Seat(placeName, idPlace);
                seats.add(seat);
            }
        }

        Intent intentFromSessionSelection = getIntent();
        String sessionID = intentFromSessionSelection.getStringExtra("sessionID");
        String filmTitle = intentFromSessionSelection.getStringExtra("filmTitle");
        String sessionTime = intentFromSessionSelection.getStringExtra("sessionTime");

        Toast noReservationToast = Toast.makeText(this,
                "Please select a seat for your reservation",
                Toast.LENGTH_LONG);
        Intent intentToReservation = new Intent(this, ConfirmReservation.class);
        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedSeat.size() == 0) {
                    noReservationToast.show();
                    return;
                }
                intentToReservation.putStringArrayListExtra("Taken seats", selectedSeat);
                intentToReservation.putExtra("SessionID", sessionID);
                intentToReservation.putExtra("filmTitle", filmTitle);
                intentToReservation.putExtra("sessionTime", sessionTime);
                startActivity(intentToReservation);
            }
        });
        System.out.println("SessionID : " + sessionID);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Putting the attributes to the seats & their availability
        FirebaseFirestore.getInstance().collection("ticket")
                .whereEqualTo("id_seance", sessionID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isComplete()) {
                            List<String> occupiedSeats = new ArrayList<>();
                            // Getting all the places that are taken
                            for (DocumentSnapshot seatDocument : task.getResult()) {
                                String idSeat = seatDocument.getString("place");
                                occupiedSeats.add(idSeat);
                                System.out.println(idSeat);
                            }

                            // Putting the setting on the seats
                            char[] ranks = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
                            int[] columns = {1,2,3,4};

                            for (Seat seat: seats) {
                                ImageButton imageSeat = (ImageButton) findViewById(seat.getAndroidID());
                                if (occupiedSeats.contains(seat.getId())) {
                                    imageSeat.setImageResource(R.drawable.seat_no_available);
                                    imageSeat.setClickable(false);
                                    imageSeat.setEnabled(false);
                                } else {
                                    imageSeat.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Drawable.ConstantState imgId1 = imageSeat.getDrawable().getConstantState();
                                            Drawable.ConstantState imgId2 = getDrawable(R.drawable.seat_no_available).getConstantState();
                                            String seatName = searchSeatNameFromeId(imageSeat.getId());
                                            if (!imgId1.equals(imgId2)) {
                                                imageSeat.setImageResource(R.drawable.seat_no_available);
                                                selectedSeat.add(seatName);
                                            } else {
                                                imageSeat.setImageResource(R.drawable.seat_available);
                                                selectedSeat.remove(seatName);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                });
    }

    public String searchSeatNameFromeId(int androidSeatId) {
        int x = 0, y = 0;
        char[] ranks = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[] columns = {1,2,3,4};
        String seatName = "";
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                if (androidIDSeats[i][j] == androidSeatId) {
                    seatName = "" + ranks[i] + columns[j];
                }
            }
        }
        return seatName;
    }

}