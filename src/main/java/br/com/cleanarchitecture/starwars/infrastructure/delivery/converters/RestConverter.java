package br.com.cleanarchitecture.starwars.infrastructure.delivery.converters;

import java.io.Serializable;

public interface RestConverter<R extends Serializable, D extends Serializable> {
    default R mapToRest(final D domainObject) {
        throw new UnsupportedOperationException();
    }

    default D mapToDomain(final R restObject) {
        throw new UnsupportedOperationException();
    }
}