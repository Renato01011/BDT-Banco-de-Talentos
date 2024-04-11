package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContactInfoReq {

    @NotNull(message = "El Id del Talento es obligatorio y no puede ser nulo.")
    private Integer id;

    @NotBlank(message = "El Número de celular es obligatorio y no puede estar vacío ni ser nulo.")
    private String celular;

    @Email(message = "El correo electrónico introducido no tiene el formato correcto.")
    @NotBlank(message = "El Email del talento es obligatorio y no puede estar vacío ni ser nulo.")
    private String email;
}
