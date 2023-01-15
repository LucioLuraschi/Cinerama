package com.example.cinerama;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinerama.UnitClass.Movie;
import com.example.cinerama.UnitClass.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails extends AppCompatActivity {

    Session selectedSession = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);

        // Getting the data of the movie
        Intent intentFromList = getIntent();
        String title = intentFromList.getStringExtra("movieTitle");
        String synopsis = intentFromList.getStringExtra("movieSynopsis");
        String director = intentFromList.getStringExtra("movieDirector");
        int idPoster = intentFromList.getIntExtra("moviePosterId", 0);
        String categories = intentFromList.getStringExtra("movieCategories");
        String year = intentFromList.getStringExtra("movieRelease");
        String last = intentFromList.getStringExtra("movieLast");

        // Putting the data in the page
        TextView movieTitle = findViewById(R.id.filmTitle);
        movieTitle.setText(title);
        TextView movieDirector = findViewById(R.id.filmDirector);
        movieDirector.setText(director);
        TextView movieYear = findViewById(R.id.filmYear);
        movieYear.setText(year);
        TextView movieLast = findViewById(R.id.filmLast);
        movieLast.setText(last);
        TextView movieSynopsis = findViewById(R.id.filmSynopsis);
        movieSynopsis.setText(synopsis);
        ImageView filmPoster = findViewById(R.id.filmPosterDetail);
        filmPoster.setImageResource(idPoster);
        TextView filmCategories = findViewById(R.id.filmCategories);
        filmCategories.setText(categories);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = (Button) findViewById(R.id.button6);
        Intent intent = new Intent(this, TakeMySeat.class);
        Spinner spinnerCreaneaux = (Spinner) findViewById(R.id.spinnerCreaneaux);
        TextView titreSessions = (TextView) findViewById(R.id.titleSessions);
        System.out.println(intentFromList.getStringExtra("movieID"));


        // Getting the sessions for the film
        FirebaseFirestore.getInstance().collection("seance")
                .whereEqualTo("id_film", intentFromList.getStringExtra("movieID"))
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isComplete()) {
                            // Putting the session on the selection
                            if (task.getResult().size() == 0) {
                                button.setClickable(false);
                                button.setText("No session available");
                                spinnerCreaneaux.setVisibility(View.GONE);
                                titreSessions.setVisibility(View.GONE);
                            } else {
                                List<Session> listSession = new ArrayList<>();
                                Session nullSession = new Session(
                                        (Movie) null,
                                        "-Select a session-",
                                        "0",
                                        null
                                );
                                listSession.add(nullSession);
                                for (DocumentSnapshot sessionDocument : task.getResult()) {
                                    Session session = new Session(
                                            sessionDocument.getString("id_film"),
                                            sessionDocument.getDate("diffusion").toString(),
                                            sessionDocument.getId(),
                                            sessionDocument.getBoolean("complet")
                                    );
                                    listSession.add(session);
                                }
                                ArrayAdapter<Session> listAdapterSession = new ArrayAdapter<>(
                                        spinnerCreaneaux.getContext(), android.R.layout.simple_spinner_item,
                                        listSession
                                );
                                spinnerCreaneaux.setAdapter(listAdapterSession);

                                spinnerCreaneaux.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        Adapter adapter = adapterView.getAdapter();
                                        selectedSession = (Session) adapter.getItem(i);
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                        selectedSession = null;
                                    }
                                });
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (selectedSession.getId().equals("0")) {
                                            Toast.makeText(
                                                    button.getContext(),
                                                    "Aucune session n'a été séléctionnée. Veuillez en prendre une pour continuer",
                                                    Toast.LENGTH_LONG
                                            ).show();
                                        } else {
                                            intent.putExtra("sessionID", selectedSession.getId());
                                            startActivity(intent);
                                        }
                                    }
                                });
                            }
                        }
                    }
                });

    }
}
