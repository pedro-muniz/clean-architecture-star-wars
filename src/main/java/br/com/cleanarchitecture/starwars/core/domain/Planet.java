package br.com.cleanarchitecture.starwars.core.domain;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
public class Planet implements Serializable {
    @NonNull
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String terrain;

    @NonNull
    private String climate;

    private Planet() {}
}
