package br.com.cleanarchitecture.starwars.core.usecases;

import br.com.cleanarchitecture.starwars.core.ports.thirdpartyapi.PlanetThirdPartyApi;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetPlanetMoviesUseCaseImpl implements GetPlanetMoviesUseCase{
    private final PlanetThirdPartyApi planetThirdPartyApi;

    @Override
    public String[] execute(String name) {
        return planetThirdPartyApi.getPlanetMovies(name);
    }
}
