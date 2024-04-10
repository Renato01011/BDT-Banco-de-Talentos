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
public class AddSoftSkillReq {

    @NotNull(message = "El Id del Talento es obligatorio y no puede ser nulo.")
    private Integer id;

    @NotBlank(message = "El nombre de la Habilidad blanda es obligatorio y no puede estar vacío ni ser nulo.")
    private String nombre;
}
