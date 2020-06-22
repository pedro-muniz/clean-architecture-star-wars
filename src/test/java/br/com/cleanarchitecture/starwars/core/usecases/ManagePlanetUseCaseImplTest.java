package br.com.cleanarchitecture.starwars.core.usecases;

import br.com.cleanarchitecture.starwars.core.domain.Planet;
import br.com.cleanarchitecture.starwars.core.exceptions.PlanetAlreadyExistsException;
import br.com.cleanarchitecture.starwars.core.ports.repository.PlanetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ManagePlanetUseCaseImplTest {
    @Mock
    private PlanetRepository planetRepository;

    private  ManagePlanetUseCase managePlanetUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        managePlanetUseCase = spy(new ManagePlanetUseCaseImpl(planetRepository));
    }

    @Test
    @DisplayName("Given a valid planet " +
            "When call save " +
            "Should verify if planet exists " +
            "       not throws IllegalArgumentException or PlanetAlreadyExistsException" +
            "       and return a planet with id")
    void save_allGood_shouldPass() {
        //Arrange
        Planet planet = new Planet(null, "Tatooine", "Dessert", "Arid");

        String fakeObjectId = "58d1c36efb0cac4e15afd202";
        Answer<Planet> repositoryAnswer = invocation -> new Planet(fakeObjectId, "Tatooine", "Dessert", "Arid");

        when(planetRepository.save(planet)).thenAnswer(repositoryAnswer);

        //Com objetos "spy" uso o doReturn para que não haja efeito colateral no teste.
        doReturn(Optional.empty()).when(managePlanetUseCase).findByName(anyString());

        //Act
        Planet savedPlanet = null;
        try {
            savedPlanet = managePlanetUseCase.save(planet);
        } catch (IllegalArgumentException illegalArgumentException) {
            fail("IllegalArgumentException throws with a valid planet");
        } catch (PlanetAlreadyExistsException planetAlreadyExistsException) {
            fail("IllegalArgumentException throws with a new planet");
        }

        //Assert
        assertSame(fakeObjectId, savedPlanet.getId());
    }

    @Test
    @DisplayName("Given an invalid planet " +
            "When call save with null planet " +
            "Should throws IllegalArgumentException")
    void save_nullPlanet_shouldThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            managePlanetUseCase.save(null);
        });
    }

    @Test
    @DisplayName("Given an existent planet " +
            "When call save " +
            "Should throws PlanetAlreadyExistsException")
    void save_existentPlanet_shouldThrowsPlanetAlreadyExistsException() {
        String fakeObjectId = "58d1c36efb0cac4e15afd202";
        Planet planet = new Planet(fakeObjectId, "Tatooine", "Dessert", "Arid");

        //Com objetos "spy" uso o doReturn para que não haja efeito colateral no teste.
        doReturn(Optional.of(planet)).when(managePlanetUseCase).findByName(anyString());

        assertThrows(PlanetAlreadyExistsException.class, () -> {
            managePlanetUseCase.save(planet);
        });
    }

    @Test
    @DisplayName("Given user wants to get a planet by name " +
            "When call findByName method with an existent planet name " +
            "Then should return the planet" +
            "     should not throw IllegalArgumentException")
    void findByName_allGood_shouldReturnThePlanet() {
        //Arrange
        String fakeObjectId = "58d1c36efb0cac4e15afd202";
        String fakeName = "Tatooine";
        Planet planet = new Planet(fakeObjectId, fakeName, "Dessert", "Arid");

        when(planetRepository.findOneByName(planet.getName())).thenReturn(Optional.of(planet));

        //Act
        Optional<Planet> repoPlanet = Optional.empty();
        try {
            repoPlanet = managePlanetUseCase.findByName(fakeName);
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException throws with a valid planet");
        }

        assertTrue(repoPlanet.isPresent());
        assertSame(repoPlanet.get().getId(), planet.getId());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "            "})
    @NullSource
    @DisplayName("Given user wants to get a planet by name " +
            "When call findByName method with invalid name " +
            "Then should throw IllegalArgumentException")
    void findByName_invalidName_shouldThrowsIllegalArgumentException(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            managePlanetUseCase.findByName(name);
        });
    }

    @Test
    @DisplayName("Given user wants to get a planet by name " +
            "When call findByName with nonexistent planet name " +
            "Then should not throw IllegalArgumentException" +
            "   planet should not be present")
    void findByName_allGood_shouldReturnOptionalEmpty() {
        //Arrange
        String fakeName = "Tatooine";
        String fakeObjectId = "58d1c36efb0cac4e15afd202";
        String nonExistentName = "Terra";
        Planet planet = new Planet(fakeObjectId, fakeName, "Dessert", "Arid");

        when(planetRepository.findOneByName(fakeName)).thenReturn(Optional.of(planet));

        //Act
        Optional<Planet> repoPlanet = Optional.empty();
        try {
            repoPlanet = managePlanetUseCase.findByName(nonExistentName);
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException throws with a valid planet");
        }

        assertFalse(repoPlanet.isPresent());
    }

    @Test
    @DisplayName("Given user wants to get a planet by name " +
            "When call findById method with an existent planet name " +
            "Then should return the planet" +
            "     should not throw IllegalArgumentException")
    void findById_allGood_shouldReturnThePlanet() {
        //Arrange
        String fakeObjectId = "58d1c36efb0cac4e15afd202";
        String fakeName = "Tatooine";
        Planet planet = new Planet(fakeObjectId, fakeName, "Dessert", "Arid");

        when(planetRepository.findOneById(planet.getId())).thenReturn(Optional.of(planet));

        //Act
        Optional<Planet> repoPlanet = Optional.empty();
        try {
            repoPlanet = managePlanetUseCase.findById(fakeObjectId);
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException throws with a valid planet");
        }

        assertTrue(repoPlanet.isPresent());
        assertSame(repoPlanet.get().getId(), planet.getId());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "            "})
    @NullSource
    @DisplayName("Given user wants to get a planet by name " +
            "When call findById method with invalid id " +
            "Then should throw IllegalArgumentException")
    void findById_invalidId_shouldThrowsIllegalArgumentException(String id) {
        assertThrows(IllegalArgumentException.class, () -> {
            managePlanetUseCase.findById(id);
        });
    }

    @Test
    @DisplayName("Given user wants to get a planet by name " +
            "When call findById with nonexistent planet name " +
            "Then should not throw IllegalArgumentException" +
            "   planet should not be present")
    void findById_allGood_shouldReturnOptionalEmpty() {
        //Arrange
        String fakeName = "Tatooine";
        String fakeObjectId = "58d1c36efb0cac4e15afd202";
        String nonExistentId = "NONONONONO";
        Planet planet = new Planet(fakeObjectId, fakeName, "Dessert", "Arid");
        when(planetRepository.findOneById(fakeObjectId)).thenReturn(Optional.of(planet));

        //Act
        Optional<Planet> repoPlanet = Optional.empty();
        try {
            repoPlanet = managePlanetUseCase.findById(nonExistentId);
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException throws with a valid planet");
        }

        assertFalse(repoPlanet.isPresent());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "            "})
    @NullSource
    @DisplayName("Given user wants to delete a planet  " +
            "When call delete method with invalid id " +
            "Then should throw IllegalArgumentException")
    void delete_invalidId_shouldThrowsIllegalArgumentException(String id) {
        assertThrows(IllegalArgumentException.class, () -> {
            managePlanetUseCase.delete(id);
        });
    }
}