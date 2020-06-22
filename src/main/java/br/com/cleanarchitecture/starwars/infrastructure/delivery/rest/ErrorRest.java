package br.com.cleanarchitecture.starwars.infrastructure.delivery.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorRest {
    private String error;
    private int errorCode;
    private String path;
    private Long timestamp;
}
