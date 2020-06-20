package br.com.cleanarchitecture.starwars.core.ports.repository;

import br.com.cleanarchitecture.starwars.core.domain.Planet;

import java.util.List;
import java.util.Optional;

public interface PlanetRepository {
    Planet save(Planet planet);

    List<Planet> findAll();

    Optional<Planet> findOneByName(String name);

    Optional<Planet> findOneById(String id);

    void delete(String id);
}
