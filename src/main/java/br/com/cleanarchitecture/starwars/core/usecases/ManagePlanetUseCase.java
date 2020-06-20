package br.com.cleanarchitecture.starwars.core.usecases;

import br.com.cleanarchitecture.starwars.core.domain.Planet;
import java.util.List;
import java.util.Optional;

public interface ManagePlanetUseCase {
    Planet save(Planet planet);

    List<Planet> getAll();

    Optional<Planet> findByName(String name);

    Optional<Planet> findById(String id);

    void delete(String id);
}
