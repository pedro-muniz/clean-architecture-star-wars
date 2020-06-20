package br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SwapiPlanetApiResponse implements Serializable {
    private String climate;
    private String created;
    private String diameter;
    private String edited;
    List<String> films;
    private String gravity;
    private String name;
    private String orbital_period;
    private String population;
    List<String> residents;
    private String rotation_period;
    private String surface_water;
    private String terrain;
    private String url;
}
