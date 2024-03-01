package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciasLaborales {
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String empresa;
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String puesto;
    @NotNull(message = "Este campo no puede ser nulo")
    private Date fechaInicio;
    @NotNull(message = "Este campo no puede ser nulo")
    private Date fechaFin;
}
