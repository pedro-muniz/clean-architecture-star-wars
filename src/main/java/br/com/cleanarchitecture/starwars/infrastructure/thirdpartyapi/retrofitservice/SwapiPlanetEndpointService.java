package br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.retrofitservice;

import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.response.SwapiApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SwapiPlanetEndpointService {
    @GET("planets/")
    Call<SwapiApiResponse> getPlanet(@Query("name") String name);
}
