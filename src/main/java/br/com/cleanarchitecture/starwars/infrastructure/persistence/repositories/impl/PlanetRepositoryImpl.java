package br.com.cleanarchitecture.starwars.infrastructure.persistence.repositories.impl;

import br.com.cleanarchitecture.starwars.core.domain.Planet;
import br.com.cleanarchitecture.starwars.core.ports.repository.PlanetRepository;
import br.com.cleanarchitecture.starwars.infrastructure.persistence.converters.PlanetRepositoryConverter;
import br.com.cleanarchitecture.starwars.infrastructure.persistence.entities.PlanetEntity;
import br.com.cleanarchitecture.starwars.infrastructure.persistence.repositories.mongodb.PlanetMongoDbRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PlanetRepositoryImpl implements PlanetRepository {
    private final PlanetMongoDbRepository planetMongoDbRepository;

    private final PlanetRepositoryConverter planetRepositoryConverter;

    @Override
    public Planet save(Planet planet) {
        PlanetEntity entity = planetRepositoryConverter.mapToMongoDb(planet);
        PlanetEntity saved = planetMongoDbRepository.save(entity);
        return planetRepositoryConverter.mapToDomain(saved);
    }

    @Override
    public List<Planet> findAll() {
        List<PlanetEntity> planetEntities = planetMongoDbRepository.findAll();
        return planetEntities.stream().map(planetRepositoryConverter::mapToDomain).collect(Collectors.toList());
    }

    private Optional<Planet> getPlanet(Optional<PlanetEntity> planetEntity) {
        Optional<Planet> planet = Optional.empty();
        if (planetEntity.isPresent()) {
            planet = Optional.of(planetRepositoryConverter.mapToDomain(planetEntity.get()));
        }

        return planet;
    }

    @Override
    public Optional<Planet> findOneByName(String name) {
        Optional<PlanetEntity> entity = planetMongoDbRepository.findOneByName(name);
        return getPlanet(entity);
    }

    @Override
    public Optional<Planet> findOneById(String id) {
        Optional<PlanetEntity> entity = planetMongoDbRepository.findOneById(id);
        return getPlanet(entity);
    }

    @Override
    public void delete(String id) {
        planetMongoDbRepository.delete(id);
    }
}