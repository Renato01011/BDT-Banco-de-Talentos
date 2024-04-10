package com.fractal.bancodetalentos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HabilidadesBlandasDTO {
    @NotBlank(message = "El nombre de la Habilidad blanda es obligatorio y no puede estar vacío ni ser nulo.")
    private String nombre;
}
