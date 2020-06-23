package br.com.cleanarchitecture.starwars.infrastructure.delivery.rest;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlanetRest implements Serializable {
    @NonNull
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String terrain;

    @NonNull
    private String climate;

    private List<String> movies;

    private Integer appearances;
}
