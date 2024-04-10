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
public class UpdateDisponibilidadReq {
    @NotBlank(message = "La disponibilidad es un campo obligatorio y no puede estar vacía ni ser nula.")
    private String disponibilidad;

    @NotNull(message = "El Id del Talento es obligatorio y no puede ser nulo.")
    private Integer id;
}
