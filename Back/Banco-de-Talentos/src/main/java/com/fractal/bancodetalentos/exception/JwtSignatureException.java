package com.fractal.bancodetalentos.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class JwtSignatureException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public JwtSignatureException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
}
