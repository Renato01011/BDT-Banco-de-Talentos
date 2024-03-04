package com.fractal.bancodetalentos.handler;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.dto.ErrorDTO;
import com.fractal.bancodetalentos.model.dto.ValidationErrDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerResourceNotFoundException (ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorDTO errorDTO = ErrorDTO.builder().timestamp(new Date()).status(404).error("RESOURCE_NOT_FOUND").message(exception.getMessage()).path(webRequest.getDescription(false).replace("uri=","")).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ValidationErrDTO> errDTOS = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            String nameField = ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            errDTOS.add(new ValidationErrDTO(nameField, message));
        });
        return new ResponseEntity<>(errDTOS, HttpStatus.BAD_REQUEST);
    }
}
