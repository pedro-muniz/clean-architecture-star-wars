package br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.exceptions;

public class SwapiPlanetNotFoundException extends RuntimeException {
    public SwapiPlanetNotFoundException() {
        super();
    }

    public SwapiPlanetNotFoundException(String exception) {
        super(exception);
    }
}
