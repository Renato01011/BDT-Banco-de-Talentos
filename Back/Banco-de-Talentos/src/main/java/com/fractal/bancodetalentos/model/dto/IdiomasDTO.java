package com.fractal.bancodetalentos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdiomasDTO {
    private Integer idiomaId;
    private Integer nivelId;
    private Integer nuEstrellas;
}
