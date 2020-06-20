package br.com.cleanarchitecture.starwars.infrastructure.persistence.repositories.mongodb;
import br.com.cleanarchitecture.starwars.infrastructure.persistence.entities.PlanetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlanetMongoDbRepository extends MongoRepository<PlanetEntity, String> {
    Optional<PlanetEntity> findOneByName(String name);

    Optional<PlanetEntity> findOneById(String id);

    void delete(String id);
}
