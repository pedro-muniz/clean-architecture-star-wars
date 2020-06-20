package br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi;

import br.com.cleanarchitecture.starwars.core.ports.thirdpartyapi.PlanetThirdPartyApi;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.response.SwapiPlanetApiResponse;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.retrofitservice.SwapiPlanetEndpointService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class PlanetThirdPartyApiImpl implements PlanetThirdPartyApi {
    private final Retrofit retrofit;

    public PlanetThirdPartyApiImpl() {
        final String SWAPI_PLANETS_URL = "https://swapi.co/api/planets";
        retrofit = new Retrofit.Builder().baseUrl(SWAPI_PLANETS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private SwapiPlanetApiResponse getFromApi(String name) {
        SwapiPlanetEndpointService swapiPlanetEndpointService =  retrofit.create(SwapiPlanetEndpointService.class);
        Call<SwapiPlanetApiResponse> call = swapiPlanetEndpointService.getPlanet(name);
        Response<SwapiPlanetApiResponse> response;
        try {
            response = call.execute();
        } catch(IOException e) {
            throw new IllegalStateException("Can't access swapi api.");
        }

        return response.body();
    }

    @Override
    public List<String> getPlanetMovies(String name) {
        SwapiPlanetApiResponse response = getFromApi(name);
        return response.getFilms();
    }
}
