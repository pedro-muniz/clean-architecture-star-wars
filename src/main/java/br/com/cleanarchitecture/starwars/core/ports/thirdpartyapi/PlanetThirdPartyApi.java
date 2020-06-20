package br.com.cleanarchitecture.starwars.core.ports.thirdpartyapi;

import java.util.List;

public interface PlanetThirdPartyApi {
    List<String> getPlanetMovies(String name);
}
