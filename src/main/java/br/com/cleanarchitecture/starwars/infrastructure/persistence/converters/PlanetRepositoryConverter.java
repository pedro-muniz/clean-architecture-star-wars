package br.com.cleanarchitecture.starwars.infrastructure.persistence.converters;

import br.com.cleanarchitecture.starwars.core.domain.Planet;
import br.com.cleanarchitecture.starwars.infrastructure.persistence.entities.PlanetEntity;

public class PlanetRepositoryConverter implements RepositoryConverter<PlanetEntity, Planet>  {
    @Override
    public PlanetEntity mapToMongoDb(final Planet planet) {
        return new PlanetEntity(planet.getId(), planet.getName(), planet.getTerrain(), planet.getClimate());
    }

    @Override
    public Planet mapToDomain(final PlanetEntity planetEntity) {
        return new Planet(planetEntity.getId(), planetEntity.getName(), planetEntity.getTerrain(), planetEntity.getClimate());
    }
}
