package com.fractal.bancodetalentos.model.request;

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
public class Idiomas {
    @NotNull(message = "Este campo no puede ser nulo")
    @Min(value = 1, message = "Debe ser un numero igual o mayor a 1")
    @Max(value = 3, message = "Debe ser un numero igual o menor a 3")
    private Integer idiomaId;
    @NotNull(message = "Este campo no puede ser nulo")
    @Min(value = 1, message = "Debe ser un numero igual o mayor a 1")
    @Max(value = 4, message = "Debe ser un numero igual o menor a 4")
    private Integer nivelId;
    @NotNull(message = "Este campo no puede ser nulo")
    @Min(value = 0, message = "Debe ser un numero positivo o cero")
    @Max(value = 5, message = "Debe ser un numero igual o menor a 5")
    private Integer nuEstrellas;
}
