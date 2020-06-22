package br.com.cleanarchitecture.starwars.infrastructure.delivery.controllers;

import br.com.cleanarchitecture.starwars.core.domain.Planet;
import br.com.cleanarchitecture.starwars.core.usecases.ManagePlanetUseCase;
import br.com.cleanarchitecture.starwars.core.usecases.AddPlanetMoviesUseCase;
import br.com.cleanarchitecture.starwars.infrastructure.delivery.converters.RestConverter;
import br.com.cleanarchitecture.starwars.infrastructure.delivery.responses.RestResponse;
import br.com.cleanarchitecture.starwars.infrastructure.delivery.rest.PlanetRest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/planet")
public class PlanetController {
    private final ManagePlanetUseCase managePlanetUseCase;

    private final AddPlanetMoviesUseCase addPlanetMoviesUseCase;

    private final RestConverter<PlanetRest, Planet> restConverter;

    @PostMapping
    public RestResponse<PlanetRest> save(@Valid @RequestBody PlanetRest planetRest, HttpServletResponse response) {
        Planet planet = restConverter.mapToDomain(planetRest);
        PlanetRest savedPlanet = restConverter.mapToRest(managePlanetUseCase.save(planet));
        return new RestResponse<>(HttpStatus.OK, "Planeta inserido com sucesso.", savedPlanet);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RestResponse<PlanetRest> getById(@PathVariable String id) {
        Optional<Planet> planet = managePlanetUseCase.findById(id);
        planet.ifPresent(addPlanetMoviesUseCase::execute);

        PlanetRest planetRest = restConverter.mapToRest(planet.orElse(null));
        return new RestResponse<>(HttpStatus.OK, null, planetRest);
    }

    @RequestMapping(value = "/find/", method = RequestMethod.GET)
    public RestResponse<PlanetRest> getByName(@RequestParam String name) {
        Optional<Planet> planet = managePlanetUseCase.findByName(name);
        planet.ifPresent(addPlanetMoviesUseCase::execute);

        PlanetRest planetRest = restConverter.mapToRest(planet.orElse(null));
        return new RestResponse<>(HttpStatus.OK, null, planetRest);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public RestResponse<List<PlanetRest>> getAll() {
        List<Planet> planets = managePlanetUseCase.getAll();

        List<PlanetRest> planetsRests = new ArrayList<>();
        for (Planet planet : planets) {
            addPlanetMoviesUseCase.execute(planet);
            planetsRests.add(restConverter.mapToRest(planet));
        }

        return new RestResponse<>(HttpStatus.OK, null, planetsRests);
    }

    @DeleteMapping("/{id}")
    public RestResponse<Void> delete(@PathVariable String id) {
        managePlanetUseCase.delete(id);
        return new RestResponse<>(HttpStatus.OK, "Planeta removido com sucesso.");
    }
}
