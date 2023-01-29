package com.example.cinerama.UnitClass;

import java.util.TreeMap;

public class Ticket {
    private Session session;
    private String idTicket;
    private Seat seat;
    private String movieName;
    private String sessionTime;
    private String seatName;

    public Ticket(Seat seat, Session session) {
        this.seat = seat;
        this.session = session;
    }

    public Ticket(String id, String movieName, String sessionTime, String seatName) {
        this.idTicket = id;
        this.movieName = movieName;
        this.sessionTime = sessionTime;
        this.seatName = seatName;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public String getReservationString() {
        return this.movieName + " - " + this.seat + " - " + this.sessionTime;
    }
}
