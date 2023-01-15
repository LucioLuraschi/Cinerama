package com.example.cinerama.UnitClass;


import androidx.annotation.NonNull;

import java.util.Date;

public class Session {

    private String id;
    private String movieId;
    private Movie movie;
    private String showDate;
    private Boolean isFull;

    public Session(Movie movie, String showDate, String id, Boolean isFull) {
        this.movie = movie;
        this.showDate = showDate;
        this.id = id;
        this.isFull = isFull;
    }

    public Session(String movieId, String showDate, String id, Boolean isFull) {
        this.movieId = movieId;
        this.showDate = showDate;
        this.id = id;
        this.isFull = isFull;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public Boolean getFull() {
        return isFull;
    }

    @NonNull
    @Override
    public String toString() {
        return showDate;
    }
}
