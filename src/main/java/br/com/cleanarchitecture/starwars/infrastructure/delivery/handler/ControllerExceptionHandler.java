package br.com.cleanarchitecture.starwars.infrastructure.delivery.handler;

import br.com.cleanarchitecture.starwars.core.exceptions.PlanetAlreadyExistsException;
import br.com.cleanarchitecture.starwars.core.exceptions.PlanetNotFoundException;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.exceptions.SwapiApiException;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.exceptions.SwapiPlanetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(PlanetAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Esse planeta já foi cadastrado.")
    public void planetAlreadyExists(PlanetAlreadyExistsException ex) {}

    @ExceptionHandler(PlanetNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Planeta não encontrado.")
    public void planetNotFound(PlanetNotFoundException ex) {}

    @ExceptionHandler(SwapiApiException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "A api do SWAPI não retornou uma resposta válida.")
    public void swapiApi(SwapiApiException ex) {}

    @ExceptionHandler(SwapiPlanetNotFoundException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Planeta não encontrado na API do SWAPI.")
    public void  swapiPlanetResultApi(SwapiPlanetNotFoundException ex) {}

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "O request foi passado com argumentos inválidos.")
    public void illegalArgument(IllegalArgumentException ex) {}
}
