package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HabilidadesBlandas {
    @NotBlank(message = "El nombre de la Habilidad blanda es obligatorio y no puede estar vacío ni ser nulo.")
    private String nombre;
}
