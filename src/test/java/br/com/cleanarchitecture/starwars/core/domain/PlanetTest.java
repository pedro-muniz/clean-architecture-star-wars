package br.com.cleanarchitecture.starwars.core.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {
    @Test
    void addMoviesInfo_allGood_shouldPass(){
        //Arrange
        String fakeObjectId = "58d1c36efb0cac4e15afd202";
        Planet planet = new Planet(fakeObjectId, "Tatooine", "Dessert", "Arid");

        List<String> movies = new ArrayList<>();
        movies.add("https://swapi.dev/api/films/1/");
        movies.add("https://swapi.dev/api/films/2/");

        //Act
        planet.addMoviesInfo(movies);

        //Assert
        assertAll(
                () -> assertEquals(movies, planet.getMovies()),
                () -> assertEquals(movies.size(), planet.getAppearances())
        );
    }
}