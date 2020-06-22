package br.com.cleanarchitecture.starwars.core.usecases;

import br.com.cleanarchitecture.starwars.core.domain.Planet;
import br.com.cleanarchitecture.starwars.core.ports.thirdpartyapi.PlanetThirdPartyApi;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AddPlanetMoviesUseCaseImpl implements AddPlanetMoviesUseCase {
    private final PlanetThirdPartyApi planetThirdPartyApi;

    @Override
    public void execute(Planet planet) {
        if (planet == null) {
            throw new IllegalArgumentException("Invalid planet.");
        }

        List<String> planetMovies = planetThirdPartyApi.getPlanetMovies(planet.getName());
        planet.addMoviesInfo(planetMovies);
    }
}
