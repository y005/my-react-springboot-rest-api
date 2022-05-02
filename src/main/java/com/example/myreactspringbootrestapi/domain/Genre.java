package com.example.myreactspringbootrestapi.domain;

import java.util.Arrays;

public enum Genre {
    ADVENTURE("Adventure"),
    PUZZLE("Puzzle"),
    ACTION("Action"),
    FPS("FPS"),
    SPORTS("Sports"),
    ;

    private final String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public static Genre toGenre(String genre) {
        return Arrays.stream(values()).filter(e->e.toString().equals(genre)).findFirst().get();
    }

    @Override
    public String toString() {
        return genre;
    }
}
