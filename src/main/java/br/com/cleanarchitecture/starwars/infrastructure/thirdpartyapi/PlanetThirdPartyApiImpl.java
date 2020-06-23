package br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi;

import br.com.cleanarchitecture.starwars.core.ports.thirdpartyapi.PlanetThirdPartyApi;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.exceptions.SwapiApiException;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.exceptions.SwapiPlanetNotFoundException;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.response.SwapiApiResponse;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.response.SwapiPlanetApiResponse;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.retrofitservice.SwapiPlanetEndpointService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.cache.annotation.Cacheable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlanetThirdPartyApiImpl implements PlanetThirdPartyApi {
    private final Retrofit retrofit;

    public PlanetThirdPartyApiImpl() {
        final String SWAPI_PLANETS_URL = "https://swapi.dev/api/";
        retrofit = new Retrofit.Builder().baseUrl(SWAPI_PLANETS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void apiErrorHandler(Response<SwapiApiResponse> response) {
        int code = response.code();
        if (code >= 200 && code < 300) {
            return;
        }

        if (code >= 400 && code < 500) {
            if (response.errorBody() != null) {
                throw new SwapiPlanetNotFoundException(response.errorBody().toString());
            }
        }

        if (response.errorBody() != null) {
            throw new SwapiApiException(response.errorBody().toString());
        }

        throw new SwapiApiException();
    }

    private SwapiApiResponse getFromApi(String name) {
        SwapiPlanetEndpointService swapiPlanetEndpointService = retrofit.create(SwapiPlanetEndpointService.class);
        Call<SwapiApiResponse> call = swapiPlanetEndpointService.getPlanet(name);
        Response<SwapiApiResponse> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new SwapiApiException("Can't access swapi api.");
        }

        apiErrorHandler(response);
        return response.body();
    }

    @Override
    @Cacheable(value = "swapiGetPlanetMovies", unless = "#result==null")
    public List<String> getPlanetMovies(String name) {
        SwapiApiResponse response = getFromApi(name);
        if (response == null) {
            throw new SwapiPlanetNotFoundException();
        }

        SwapiPlanetApiResponse swapiPlanetApiResponse = response.getResults().stream()
                .filter(p -> p.getName().equals(name)).findFirst()
                .orElseThrow(SwapiPlanetNotFoundException::new);

        if (ArrayUtils.isEmpty(swapiPlanetApiResponse.getFilms())) {
            return new ArrayList<>();
        }

        return Arrays.asList(swapiPlanetApiResponse.getFilms());
    }

    @Override
    @Cacheable(value = "swapiIsPlanetExists")
    public boolean isPlanetExists(String name) {
        SwapiApiResponse response = getFromApi(name);
        if (response == null) {
            return false;
        }

        SwapiPlanetApiResponse swapiPlanetApiResponse = response.getResults().stream()
                .filter(p -> p.getName().equals(name)).findFirst()
                .orElse(null);

        return swapiPlanetApiResponse != null;

    }
}
