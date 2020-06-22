package br.com.cleanarchitecture.starwars.core.exceptions;

public class PlanetAlreadyExistsException extends RuntimeException {
    public PlanetAlreadyExistsException() {
        super();
    }

    public PlanetAlreadyExistsException(String message) {
        super(message);
    }
}
