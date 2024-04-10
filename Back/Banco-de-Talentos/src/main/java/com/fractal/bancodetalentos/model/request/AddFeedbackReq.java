package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddFeedbackReq {

    @NotNull(message = "El Id del Talento es obligatorio y no puede ser nulo.")
    private Integer id;

    @NotNull(message = "El Número de estrellas es obligatorio y no puede ser nulo.")
    @Min(value = 0, message = "El Número de estrellas debe ser un numero positivo o cero.")
    @Max(value = 5, message = "El Número de estrellas debe ser un numero igual o menor a 5.")
    private Integer nuEstrellas;

    @NotBlank(message = "El campo Descripción es obligatorio y no puede estar vacío ni ser nulo.")
    private String descripcion;

    @NotNull(message = "El Id del usuario es obligatorio y no puede ser nulo.")
    private Integer userFromId;
}
