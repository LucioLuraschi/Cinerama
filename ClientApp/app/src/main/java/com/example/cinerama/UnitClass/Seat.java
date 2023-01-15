package com.example.cinerama.UnitClass;


public class Seat {
    private String id;
    private String name;
    private Boolean isOccupied;

    public Seat(String id, String name, Boolean isOccupied) {
        this.id = id;
        this.name = name;
        this.isOccupied = isOccupied;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
}
