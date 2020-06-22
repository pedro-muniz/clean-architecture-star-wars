package br.com.cleanarchitecture.starwars.infrastructure.delivery.handler;

import br.com.cleanarchitecture.starwars.core.exceptions.PlanetAlreadyExistsException;
import br.com.cleanarchitecture.starwars.core.exceptions.PlanetNotFoundException;
import br.com.cleanarchitecture.starwars.infrastructure.delivery.responses.RestResponse;
import br.com.cleanarchitecture.starwars.infrastructure.delivery.rest.ErrorRest;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.exceptions.SwapiApiException;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.exceptions.SwapiPlanetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(PlanetAlreadyExistsException.class)
    public RestResponse<ErrorRest> planetAlreadyExists(PlanetAlreadyExistsException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorRest err = new ErrorRest(ex.getMessage(), status.value(), request.getRequestURI(), System.currentTimeMillis());

        final String ALREADY_EXISTS_MESSAGE = "Esse planeta já foi cadastrado.";
        return new RestResponse<>(status, ALREADY_EXISTS_MESSAGE, err);
    }

    @ExceptionHandler(PlanetNotFoundException.class)
    public RestResponse<ErrorRest> planetNotFound(PlanetNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorRest err = new ErrorRest(ex.getMessage(), status.value(), request.getRequestURI(), System.currentTimeMillis());
        final String NOT_FOUND_MESSAGE = "Planeta não encontrado.";

        return new RestResponse<>(status, NOT_FOUND_MESSAGE, err);
    }

    @ExceptionHandler(SwapiApiException.class)
    public RestResponse<ErrorRest> swapiApi(SwapiApiException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        ErrorRest err = new ErrorRest(ex.getMessage(), status.value(), request.getRequestURI(), System.currentTimeMillis());
        final String SWAPI_API_MESSAGE = "A api do SWAPI não retornou uma resposta válida.";

        return new RestResponse<>(status, SWAPI_API_MESSAGE, err);
    }

    @ExceptionHandler(SwapiPlanetNotFoundException.class)
    public RestResponse<ErrorRest> swapiPlanetResultApi(SwapiPlanetNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorRest err = new ErrorRest(ex.getMessage(), status.value(), request.getRequestURI(), System.currentTimeMillis());
        final String SWAPI_API_MESSAGE = "Planeta não encontrado na API do SWAPI.";

        return new RestResponse<>(status, SWAPI_API_MESSAGE, err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public RestResponse<ErrorRest> illegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorRest err = new ErrorRest(ex.getMessage(), status.value(), request.getRequestURI(), System.currentTimeMillis());
        final String BAD_REQUEST_MESSAGE = "O request foi passado com argumentos inválidos.";

        return new RestResponse<>(status, BAD_REQUEST_MESSAGE, err);
    }
}
