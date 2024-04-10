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
public class UpdateLanguageReq {

    @NotNull(message = "El Id de la Idioma es obligatorio y no puede ser nulo.")
    private Integer idTalentLang;

    @NotNull(message = "El Id del Talento es obligatorio y no puede ser nulo.")
    private Integer idTalent;

    @NotNull(message = "El campo Idioma es obligatorio y no puede ser nulo.")
    @Min(value = 1, message = "El campo Idioma debe ser un número igual o mayor a 1.")
    @Max(value = 3, message = "El campo Idioma debe ser un numero igual o menor a 3")
    private Integer idiomaId;
    @NotNull(message = "El campo Nivel es obligatorio y no puede ser nulo.")
    @Min(value = 1, message = "El campo Nivel debe ser un numero igual o mayor a 1")
    @Max(value = 4, message = "El campo Nivel debe ser un numero igual o menor a 4")
    private Integer nivelId;
    @NotNull(message = "El Número de estrellas es obligatorio y no puede ser nulo.")
    @Min(value = 0, message = "El Número de estrellas debe ser un numero positivo o cero.")
    @Max(value = 5, message = "El Número de estrellas debe ser un numero igual o menor a 5.")
    private Integer nuEstrellas;
}
