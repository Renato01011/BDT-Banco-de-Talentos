package com.fractal.bancodetalentos.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Integer fieldValue;


    public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue) {
        super(String.format("El recurso '%s' con: %s = '%s' no fue encontrado", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName) {
        super(String.format("El recurso '%s' no fue encontrado'", resourceName));
        this.resourceName = resourceName;
    }

}
