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
    @NotBlank(message = "El nombre de la Institución es obligatorio y no puede estar vacío ni ser nulo.")
    private String institucion;
    @NotBlank(message = "El nombre de la Carrera es obligatorio y no puede estar vacío ni ser nulo.")
    private String carrera;
    @NotBlank(message = "El nombre de la Grado es obligatorio y no puede estar vacío ni ser nulo.")
    private String grado;
    @NotNull(message = "La fecha de inicio es obligatoria y no puede ser nula.")
    @Past(message = "La fecha de inicio debe ser una fecha anterior a la actual.")
    private Date fechaInicio;
    @NotNull(message = "La fecha fin es obligatoria y no puede ser nula.")
    private Date fechaFin;
    @NotNull(message = "El campo 'Hasta la actualidad' es obligatorio y no puede ser nulo.")
    private Integer flActualidad;
}
