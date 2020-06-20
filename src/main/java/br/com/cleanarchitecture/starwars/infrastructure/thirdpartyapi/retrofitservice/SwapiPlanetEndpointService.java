package br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.retrofitservice;

import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.response.SwapiPlanetApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SwapiPlanetEndpointService {
    @GET("planets/{name}")
    Call<SwapiPlanetApiResponse> getPlanet(@Path("name") String name);
}
