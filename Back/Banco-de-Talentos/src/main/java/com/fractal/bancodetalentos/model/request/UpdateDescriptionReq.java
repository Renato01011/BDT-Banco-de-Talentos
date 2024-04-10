package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDescriptionReq {
    @NotBlank(message = "La descripción es un campo obligatorio y no puede estar vacía ni ser nula.")
    private String description;

    @NotNull(message = "El Id del Talento es obligatorio y no puede ser nulo.")
    private Integer id;
}