package com.fractal.bancodetalentos.handler;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerResourceNotFoundException (ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorDTO errorDTO = ErrorDTO.builder().timestamp(new Date()).status(404).error("RESOURCE_NOT_FOUND").message(exception.getMessage()).path(webRequest.getDescription(false).replace("uri=","")).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
