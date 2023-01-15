package com.example.cinerama;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinerama.UnitClass.Movie;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SeeMovieList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_movie_list_activity);

        // Set the intent
        Intent intent = new Intent(this, MovieDetails.class);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseFirestore.getInstance().collection("films").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isComplete()) {
                            // Building the movies class
                            List<Movie> movieList = new ArrayList<>();
                            for (DocumentSnapshot filmDocument : task.getResult()) {
                                Long movieLast = filmDocument.getLong("duree");
                                String movieLastString = ((movieLast - (movieLast % 60)) / 60) + "h";
                                if ((movieLast % 60) < 10) {
                                    movieLastString += ("0" + (movieLast % 60));
                                } else {
                                    movieLastString += (movieLast % 60);
                                }
                                Movie movie = new Movie(
                                        filmDocument.getString("nom"),
                                        filmDocument.getString("realisateur"),
                                        filmDocument.getString("date"),
                                        movieLastString,
                                        ((List<String>) filmDocument.get("genre")),
                                        filmDocument.getString("synopsis"),
                                        filmDocument.getId()
                                );
                                movieList.add(movie);
                                System.out.println(movie.getFilmID());
                            }

                            // Setting the propositions
                            Movie movie1 = movieList.get(0);
                            ImageButton imageButton = (ImageButton) findViewById(R.id.Film1);
                            imageButton.setImageResource(R.drawable.film1);
                            TextView titreText = (TextView) findViewById(R.id.TitreFilm1);
                            titreText.setText(movie1.getName());
                            imageButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    intent.putExtra("movieTitle", movie1.getName());
                                    intent.putExtra("movieSynopsis", movie1.getSynopsis());
                                    intent.putExtra("movieLast", movie1.getMovieLast());
                                    intent.putExtra("movieDirector", movie1.getNameDirector());
                                    intent.putExtra("movieRelease", movie1.getReleaseDate());
                                    intent.putExtra("movieCategories", movie1.getCategoriesString());
                                    intent.putExtra("movieID", movie1.getFilmID());
                                    intent.putExtra("moviePosterId", R.drawable.film1);
                                    startActivity(intent);
                                }
                            });

                            Movie movie2 = movieList.get(1);
                            ImageButton imageButton2 = (ImageButton) findViewById(R.id.Film2);
                            imageButton2.setImageResource(R.drawable.film2);
                            TextView titreText2 = (TextView) findViewById(R.id.TitreFilm2);
                            titreText2.setText(movie2.getName());
                            imageButton2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    intent.putExtra("movieTitle", movie2.getName());
                                    intent.putExtra("movieSynopsis", movie2.getSynopsis());
                                    intent.putExtra("movieLast", movie2.getMovieLast());
                                    intent.putExtra("movieDirector", movie2.getNameDirector());
                                    intent.putExtra("movieRelease", movie2.getReleaseDate());
                                    intent.putExtra("movieCategories", movie2.getCategoriesString());
                                    intent.putExtra("movieID", movie2.getFilmID());
                                    intent.putExtra("moviePosterId", R.drawable.film2);
                                    startActivity(intent);
                                }
                            });

                            Movie movie3 = movieList.get(2);
                            ImageButton imageButton3 = (ImageButton) findViewById(R.id.Film3);
                            imageButton3.setImageResource(R.drawable.film3);
                            TextView titreText3 = (TextView) findViewById(R.id.TitreFilm3);
                            titreText3.setText(movie3.getName());
                            imageButton3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    intent.putExtra("movieTitle", movie3.getName());
                                    intent.putExtra("movieSynopsis", movie3.getSynopsis());
                                    intent.putExtra("movieLast", movie3.getMovieLast());
                                    intent.putExtra("movieDirector", movie3.getNameDirector());
                                    intent.putExtra("movieRelease", movie3.getReleaseDate());
                                    intent.putExtra("movieCategories", movie3.getCategoriesString());
                                    intent.putExtra("movieID", movie3.getFilmID());
                                    intent.putExtra("moviePosterId", R.drawable.film3);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
    }

}
