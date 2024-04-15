package com.fractal.bancodetalentos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciasLaboralesDTO {
    private String empresa;
    private String puesto;
    @Past(message = "La fecha de inicio debe ser una fecha anterior a la actual.")
    private Date fechaInicio;
    private Date fechaFin;
    private Integer flActualidad;

    @Size(max = 1000, message = "El límite máximo de caracteres es de 1000.")
    private String functions;
}
