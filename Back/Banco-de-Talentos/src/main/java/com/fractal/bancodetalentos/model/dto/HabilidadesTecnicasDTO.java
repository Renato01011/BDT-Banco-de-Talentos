package com.fractal.bancodetalentos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HabilidadesTecnicasDTO {
    @NotBlank(message = "El nombre de la Habilidad técnica es obligatorio y no puede estar vacío ni ser nulo.")
    private String nombre;
    @NotNull(message = "Los Años de experiencia son obligatorios y no puede ser nulo.")
    @Min(value = 0,  message = "Los Años de experiencia deben ser un número positivo o cero.")
    private BigDecimal anios;

}
