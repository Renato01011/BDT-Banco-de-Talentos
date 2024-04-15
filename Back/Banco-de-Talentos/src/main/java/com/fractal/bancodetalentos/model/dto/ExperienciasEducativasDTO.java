package com.fractal.bancodetalentos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciasEducativasDTO {
    private String institucion;
    private String carrera;
    private String grado;
    @Past(message = "La fecha de inicio debe ser una fecha anterior a la actual.")
    private Date fechaInicio;
    private Date fechaFin;
    private Integer flActualidad;
}
