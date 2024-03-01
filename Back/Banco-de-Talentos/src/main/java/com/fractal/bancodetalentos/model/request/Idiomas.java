package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Idiomas {
    @NotNull(message = "Este campo no puede ser nulo")
    @Min(value = 0,  message = "Debe ser un numero positivo o cero")
    private Integer idiomaId;
    @NotNull(message = "Este campo no puede ser nulo")
    @Min(value = 0,  message = "Debe ser un numero positivo o cero")
    private Integer nivelId;
    @NotNull(message = "Este campo no puede ser nulo")
    @Min(value = 0,  message = "Debe ser un numero positivo o cero")
    private Integer nuEstrellas;
}
