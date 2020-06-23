package br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi;

import br.com.cleanarchitecture.starwars.core.ports.thirdpartyapi.PlanetThirdPartyApi;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.exceptions.SwapiPlanetNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlanetThirdPartyApiImplTest {

    @Test
    @DisplayName("Given a valid planet " +
            "When get movies from swappi" +
            "Then returns a list o movies")
    void swapi_allGood_shouldReturnPlanetMovies() {
        PlanetThirdPartyApi planetThirdPartyApi = new PlanetThirdPartyApiImpl();
        List<String> movies = planetThirdPartyApi.getPlanetMovies("Tatooine");

        //https://swapi.dev/api/planets/?name=Tatooine
        assertNotNull(movies);
    }

    @Test
    @DisplayName("Given an invalid planet " +
            "When get movies from swappi" +
            "Then throws SwapiPlanetNotFoundException")
    void swapi_notFoundPlanet_shouldReturnSwapiPlanetNotFoundException() {
        PlanetThirdPartyApi planetThirdPartyApi = new PlanetThirdPartyApiImpl();
        assertThrows(SwapiPlanetNotFoundException.class, () -> planetThirdPartyApi.getPlanetMovies("INVALID_PLANET_NAME"));
    }

}