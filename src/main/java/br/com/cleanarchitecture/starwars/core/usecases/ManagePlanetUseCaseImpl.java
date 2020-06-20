package br.com.cleanarchitecture.starwars.core.usecases;

import br.com.cleanarchitecture.starwars.core.domain.Planet;
import br.com.cleanarchitecture.starwars.core.exceptions.PlanetAlreadyExistsException;
import br.com.cleanarchitecture.starwars.core.ports.repository.PlanetRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ManagePlanetUseCaseImpl implements ManagePlanetUseCase {
    private final PlanetRepository planetRepository;

    @Override
    public Planet save(Planet planet) {
        if (planet == null) {
            throw new IllegalArgumentException("Invalid planet.");
        }

        if (findByName(planet.getName()).isPresent()) {
            throw new PlanetAlreadyExistsException();
        }

        return planetRepository.save(planet);
    }

    @Override
    public List<Planet> getAll() {
        return planetRepository.findAll();
    }

    @Override
    public Optional<Planet> findByName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Invalid planet name.");
        }

        return planetRepository.findOneByName(name);
    }

    @Override
    public Optional<Planet> findById(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("Invalid planet id.");
        }

        return planetRepository.findOneById(id);
    }

    @Override
    public void delete(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("Invalid planet id.");
        }

        planetRepository.delete(id);
    }
}
