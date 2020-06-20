package br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi;

import br.com.cleanarchitecture.starwars.core.ports.thirdpartyapi.PlanetThirdPartyApi;

public class PlanetThirdPartyApiImpl implements PlanetThirdPartyApi {
    @Override
    public String[] getPlanetMovies(String name) {
        return new String[0];
    }
}
