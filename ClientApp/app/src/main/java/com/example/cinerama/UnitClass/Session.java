package com.example.cinerama.UnitClass;


import java.util.Date;

public class Session {
    Movie movie;
    private Date showDate;
    private String hourShow;

    public Session(Movie movie, Date showDate, String hourShow) {
        this.movie = movie;
        this.showDate = showDate;
        this.hourShow = hourShow;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    public String getHourShow() {
        return hourShow;
    }

    public void setHourShow(String hourShow) {
        this.hourShow = hourShow;
    }
}
