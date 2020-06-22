package br.com.cleanarchitecture.starwars.core.usecases;

import br.com.cleanarchitecture.starwars.core.domain.Planet;

public interface AddPlanetMoviesUseCase {
    void execute(Planet planet);
}
