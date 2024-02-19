package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciasEducativas {
    private String institucion;
    private String carrera;
    private String grado;
    private Date fechaInicio;
    private Date fechaFin;
}
