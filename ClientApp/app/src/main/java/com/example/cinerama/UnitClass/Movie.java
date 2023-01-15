package com.example.cinerama.UnitClass;


import java.util.Date;
import java.util.List;

public class Movie {

    private final String name;
    private final String nameDirector;
    private final String releaseDate;
    private final String movieLast;
    private final List<String> categories;
    private final String synopsis;
    private final String filmID;

    public Movie(String name, String nameDirector, String releaserDate, String movieLast, List<String> categories, String synopsis, String id) {
        this.name = name;
        this.nameDirector = nameDirector;
        this.releaseDate = releaserDate;
        this.movieLast = movieLast;
        this.categories = categories;
        this.synopsis = synopsis;
        this.filmID = id;
    }

    public String getName() {
        return name;
    }

    public String getNameDirector() {
        return nameDirector;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getMovieLast() {
        return movieLast;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getCategoriesString() {
        StringBuilder categories = new StringBuilder();
        for (String category: this.categories) {
            categories.append(category).append(" ");
        }
        return categories.toString().trim();
    }

    public String getFilmID() {
        return filmID;
    }
}
