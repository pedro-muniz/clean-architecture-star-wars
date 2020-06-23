package br.com.cleanarchitecture.starwars.core.usecases;

import br.com.cleanarchitecture.starwars.core.domain.Planet;
import br.com.cleanarchitecture.starwars.core.exceptions.PlanetAlreadyExistsException;
import br.com.cleanarchitecture.starwars.core.exceptions.PlanetNotFoundException;
import br.com.cleanarchitecture.starwars.core.ports.repository.PlanetRepository;
import br.com.cleanarchitecture.starwars.core.ports.thirdpartyapi.PlanetThirdPartyApi;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.exceptions.SwapiPlanetNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ManagePlanetUseCaseImpl implements ManagePlanetUseCase {
    private final PlanetRepository planetRepository;

    private final PlanetThirdPartyApi planetThirdPartyApi;

    @Override
    public Planet save(Planet planet) {
        if (planet == null || StringUtils.isBlank(planet.getName())) {
            throw new IllegalArgumentException("Invalid planet.");
        }

        if (findByName(planet.getName()).isPresent()) {
            throw new PlanetAlreadyExistsException();
        }

        if (!planetThirdPartyApi.isPlanetExists(planet.getName())) {
            throw new SwapiPlanetNotFoundException();
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

        Optional<Planet> planet = planetRepository.findOneByName(name);
        if (!planet.isPresent()) {
            throw new PlanetNotFoundException();
        }

        return planet;
    }

    @Override
    public Optional<Planet> findById(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("Invalid planet id.");
        }

        Optional<Planet> planet = planetRepository.findOneById(id);
        if (!planet.isPresent()) {
            throw new PlanetNotFoundException();
        }

        return planet;
    }

    @Override
    public void delete(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("Invalid planet id.");
        }

        Optional<Planet> planet = findById(id);
        if (!planet.isPresent()) {
            throw new PlanetNotFoundException();
        }

        planetRepository.delete(id);
    }
}
