package com.example.myreactspringbootrestapi.domain;

import com.example.myreactspringbootrestapi.exception.NoGameGenreException;

import java.util.Arrays;
import java.util.List;

public enum Genre {
    ADVENTURE("Adventure"),
    PUZZLE("Puzzle"),
    ACTION("Action"),
    FPS("FPS"),
    SPORTS("Sports"),
    RACING("Racing"),
    ;

    private final String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public static Genre toGenre(String genre) {
        return Arrays.stream(values()).filter(e->e.toString().equals(genre)).findFirst().orElseThrow(
                ()-> {
                    List<String> validGenre = Arrays.stream(values()).map(Genre::toString).toList();
                    return new NoGameGenreException("Game genre not existed. Valid genre is " + validGenre);
                }
        );
    }

    @Override
    public String toString() {
        return genre;
    }
}
