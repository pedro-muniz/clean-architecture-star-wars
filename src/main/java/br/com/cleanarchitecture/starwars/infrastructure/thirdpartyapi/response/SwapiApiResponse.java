package br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SwapiApiResponse {
    private float count;
    private String next;
    private String previous = null;
    List<SwapiPlanetApiResponse> results;
}
