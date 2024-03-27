package com.fractal.bancodetalentos.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ErrorDTO {
    private Date timestamp;
    private String error;
    private String path;
    private List<ErrDTO> errList;
}
