package com.fractal.bancodetalentos.handler;

import com.fractal.bancodetalentos.exception.DuplicatedDataException;
import com.fractal.bancodetalentos.exception.JwtSignatureException;
import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.dto.ErrDTO;
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
    public ResponseEntity<ErrorDTO> handlerResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrDTO err = new ErrDTO(404, exception.getMessage());
        List<ErrDTO> errDTO = new ArrayList<>();
        errDTO.add(err);
        ErrorDTO errorDTO = ErrorDTO.builder().timestamp(new Date()).error("RESOURCE_NOT_FOUND").errList(errDTO).path(webRequest.getDescription(false).replace("uri=", "")).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        List<ErrDTO> errDTO = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            ErrDTO err = new ErrDTO();
            err.setStatus(400);
            err.setMessage(error.getDefaultMessage());
            errDTO.add(err);
        });
        ErrorDTO errorDTO = ErrorDTO.builder().timestamp(new Date()).error("BAD_REQUEST").errList(errDTO).path(webRequest.getDescription(false).replace("uri=", "")).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicatedDataException.class)
    public ResponseEntity<ErrorDTO> handlerDuplicatedDataException(DuplicatedDataException exception, WebRequest webRequest) {
        ErrDTO err = new ErrDTO(404, exception.getMessage());
        List<ErrDTO> errDTO = new ArrayList<>();
        errDTO.add(err);
        ErrorDTO errorDTO = ErrorDTO.builder().timestamp(new Date()).error("BAD_REQUEST").errList(errDTO).path(webRequest.getDescription(false).replace("uri=", "")).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtSignatureException.class)
    public ResponseEntity<ErrorDTO> handlerJwtSignatureException(JwtSignatureException exception, WebRequest webRequest) {
        ErrDTO err = new ErrDTO(404, exception.getMessage());
        List<ErrDTO> errDTO = new ArrayList<>();
        errDTO.add(err);
        ErrorDTO errorDTO = ErrorDTO.builder().timestamp(new Date()).error("BAD_REQUEST").errList(errDTO).path(webRequest.getDescription(false).replace("uri=", "")).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
