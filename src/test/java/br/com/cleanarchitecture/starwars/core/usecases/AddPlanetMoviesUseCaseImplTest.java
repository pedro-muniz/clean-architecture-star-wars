package br.com.cleanarchitecture.starwars.core.usecases;

import br.com.cleanarchitecture.starwars.core.domain.Planet;
import br.com.cleanarchitecture.starwars.core.ports.thirdpartyapi.PlanetThirdPartyApi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddPlanetMoviesUseCaseImplTest {
    @Mock
    private PlanetThirdPartyApi planetThirdPartyApi;

    private AddPlanetMoviesUseCaseImpl addPlanetMoviesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        addPlanetMoviesUseCase = new AddPlanetMoviesUseCaseImpl(planetThirdPartyApi);
    }

    @Test
    @DisplayName("Given a valid planet " +
            "When the api returns planet movies " +
            "Then should set planet movie list and appearances")
    void addPlanetMovie_allGood_shouldSetMoviesAndAppearances() {
        //Arrange
        String fakeObjectId = "58d1c36efb0cac4e15afd202";
        Planet planet = new Planet(fakeObjectId, "Tatooine", "Dessert", "Arid");
        List<String> movies = new ArrayList<>();
        movies.add("https://swapi.dev/api/films/1/");
        movies.add("https://swapi.dev/api/films/2/");

        when(planetThirdPartyApi.getPlanetMovies(planet.getName())).thenReturn(movies);

        //Act
        addPlanetMoviesUseCase.execute(planet);

        //Assert
        assertAll(
                () -> verify(planetThirdPartyApi, times(1)).getPlanetMovies(planet.getName()),
                () -> assertEquals(planet.getMovies(), movies),
                () -> assertEquals(planet.getAppearances(), movies.size())
        );
    }

    @Test
    @DisplayName("Given an invalid planet " +
            "When call method with null planet " +
            "Then should throws IllegalArgumentException")
    void addPlanetMovie_nullPlanet_shouldThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            addPlanetMoviesUseCase.execute(null);
        });
    }

    @AfterEach
    void tearDown() {
        addPlanetMoviesUseCase = null;
    }
}