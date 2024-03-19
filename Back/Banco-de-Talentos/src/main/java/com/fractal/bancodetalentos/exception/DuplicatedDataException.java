package com.fractal.bancodetalentos.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicatedDataException extends RuntimeException{

    private String fieldName;
    private String fieldList;

    public DuplicatedDataException(String fieldName) {
        super(String.format("El dato '%s' ya existe en la DB", fieldName));
        this.fieldName = fieldName;
    }

    public DuplicatedDataException(String fieldName, String fieldList) {
        super(String.format("El dato '%s' ya existe en la lista '%s'", fieldName, fieldList));
        this.fieldName = fieldName;
        this.fieldList = fieldList;
    }
}
