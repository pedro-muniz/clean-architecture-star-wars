package br.com.cleanarchitecture.starwars.infrastructure.persistence.converters;

import java.io.Serializable;

public interface RepositoryConverter<T extends Serializable, P extends Serializable> {
    default T mapToMongoDb(final P persistenceObject) {
        throw new UnsupportedOperationException();
    }

    default P mapToDomain(final T mongoObject) {
        throw new UnsupportedOperationException();
    }
}