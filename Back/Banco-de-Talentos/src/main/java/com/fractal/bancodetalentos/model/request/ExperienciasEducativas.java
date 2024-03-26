package com.fractal.bancodetalentos.model.request;

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
public class ExperienciasEducativas {
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String institucion;
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String carrera;
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String grado;
    @NotNull(message = "Este campo no puede ser nulo")
    @Past(message = "La fecha debe ser una fecha anterior a la actual")
    private Date fechaInicio;
    @NotNull(message = "Este campo no puede ser nulo")
    private Date fechaFin;
    @NotNull(message = "Este campo no puede ser nulo")
    private Integer flActualidad;
}
