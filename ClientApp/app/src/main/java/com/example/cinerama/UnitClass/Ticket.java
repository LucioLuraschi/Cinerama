package com.example.cinerama.UnitClass;

import java.util.TreeMap;

public class Ticket {
    private Session session;
    private Seat seat;

    public Ticket(Seat seat, Session session) {
        this.seat = seat;
        this.session = session;
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
}
