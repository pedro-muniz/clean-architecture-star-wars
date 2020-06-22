package br.com.cleanarchitecture.starwars.infrastructure.delivery.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class RestResponse<T> implements Serializable {
    private HttpStatus status;
    private String message;
    private T data;

    public RestResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public RestResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
