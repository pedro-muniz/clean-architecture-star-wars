package br.com.cleanarchitecture.starwars.infrastructure.delivery.converters;

import br.com.cleanarchitecture.starwars.core.domain.Planet;
import br.com.cleanarchitecture.starwars.infrastructure.delivery.rest.PlanetRest;

public class PlanetRestConverter implements RestConverter<PlanetRest, Planet> {
    @Override
    public PlanetRest mapToRest(final Planet planet) {
        if (planet == null) {
            return null;
        }

        return new PlanetRest(planet.getId(), planet.getName(), planet.getTerrain(), planet.getClimate(),
                planet.getMovies(), planet.getAppearances());
    }

    @Override
    public Planet mapToDomain(final PlanetRest planetRest) {
        if (planetRest == null) {
            return null;
        }

        return new Planet(planetRest.getId(), planetRest.getName(), planetRest.getTerrain(), planetRest.getClimate(),
                planetRest.getMovies(), planetRest.getAppearances());
    }
}
