package br.com.cleanarchitecture.starwars.infrastructure.delivery.controllers;

import br.com.cleanarchitecture.starwars.StarwarsApplication;
import br.com.cleanarchitecture.starwars.core.domain.Planet;
import br.com.cleanarchitecture.starwars.core.exceptions.PlanetNotFoundException;
import br.com.cleanarchitecture.starwars.core.usecases.AddPlanetMoviesUseCase;
import br.com.cleanarchitecture.starwars.core.usecases.ManagePlanetUseCase;
import br.com.cleanarchitecture.starwars.infrastructure.delivery.converters.PlanetRestConverter;
import br.com.cleanarchitecture.starwars.infrastructure.delivery.converters.RestConverter;
import br.com.cleanarchitecture.starwars.infrastructure.delivery.rest.PlanetRest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {StarwarsApplication.class})
class PlanetControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private ManagePlanetUseCase managePlanetUseCase;

    @MockBean
    private AddPlanetMoviesUseCase addPlanetMoviesUseCase;

    private RestConverter<PlanetRest, Planet> planetRestConverter;

    private PlanetController planetController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();

        planetRestConverter = new PlanetRestConverter();
        planetController = new PlanetController(managePlanetUseCase, addPlanetMoviesUseCase, planetRestConverter);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAll_allGood_shouldReturnHttp200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/planet/")
                .content(asJsonString(planetController))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findByName_existentPlanet_shouldReturnHttp200() throws Exception {
        String fakeObjectId = "58d1c36efb0cac4e15afd202";
        String fakeName = "Tatooine";
        Planet planet = new Planet(fakeObjectId, fakeName, "Dessert", "Arid");

        when(managePlanetUseCase.findByName(fakeName)).thenReturn(Optional.of(planet));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/planet/find/?name="+fakeName)
                .content(asJsonString(planetController))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findByName_nonexistentPlanet_shouldReturnHttp404() throws Exception {
        String fakeName = "nonexistentplanet0-33";
        when(managePlanetUseCase.findByName(fakeName)).thenThrow(PlanetNotFoundException.class);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/planet/find/?name="+fakeName)
                .content(asJsonString(new PlanetController(managePlanetUseCase, addPlanetMoviesUseCase, planetRestConverter)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @AfterEach
    void tearDown() {
        planetController = null;
    }
}