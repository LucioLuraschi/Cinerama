package com.example.cinerama.UnitClass;


import java.util.Date;

public class Movie {

    private String name;
    private String nameDirector;
    private Date releaserDate;
    private Integer note;

    public Movie(String name, String nameDirector, Date releaserDate, Integer note) {
        this.name = name;
        this.nameDirector = nameDirector;
        this.releaserDate = releaserDate;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameDirector() {
        return nameDirector;
    }

    public void setNameDirector(String nameDirector) {
        this.nameDirector = nameDirector;
    }

    public Date getReleaserDate() {
        return releaserDate;
    }

    public void setReleaserDate(Date releaserDate) {
        this.releaserDate = releaserDate;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }
}
