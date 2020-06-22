package br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.exceptions;

public class SwapiApiException extends RuntimeException {
    public SwapiApiException() {
        super();
    }

    public SwapiApiException(String exception) {
        super(exception);
    }
}
