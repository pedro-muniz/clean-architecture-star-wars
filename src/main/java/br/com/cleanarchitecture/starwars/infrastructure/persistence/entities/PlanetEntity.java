package br.com.cleanarchitecture.starwars.infrastructure.persistence.entities;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection="PLANET")
public class PlanetEntity implements Serializable {
    @Id
    private String id;

    @Field
    @NotNull
    @Indexed(unique = true)
    private String name;

    @Field
    @NotNull
    private String terrain;

    @Field
    @NotNull
    private String climate;
}
