package br.com.cleanarchitecture.starwars.core.domain;

import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
public class Planet implements Serializable {
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String terrain;

    @NonNull
    private String climate;

    private List<String> movies;

    private Integer appearances;

    private Planet() {}

    public Planet(String anId, String aName, String aTerrain, String aClimate) {
        id = anId;
        name = aName;
        terrain = aTerrain;
        climate = aClimate;
    }

    public void addMoviesInfo(List<String> movies) {
        if (CollectionUtils.isEmpty(movies)) {
            appearances = 0;
            return;
        }

        this.movies = movies;
        this.appearances = movies.size();
    }
}
