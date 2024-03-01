package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Documento {
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String nombre;
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String tipoArchivo;
    @NotNull(message = "Este campo no puede ser nulo")
    @Size(min = 1, message = "Este campo no puede estar vacío")
    @Lob
    private byte[] archivo;
}
