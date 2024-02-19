package com.fractal.bancodetalentos.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorDTO {
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
