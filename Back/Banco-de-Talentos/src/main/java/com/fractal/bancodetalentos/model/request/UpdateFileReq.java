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
public class UpdateFileReq {

    @NotNull(message = "El Id del Talento es obligatorio y no puede ser nulo.")
    private Integer idTalent;

    @NotNull(message = "El Id del Documento es obligatorio y no puede ser nulo.")
    private Integer idFile;

    @NotBlank(message = "El nombre del archivo es obligatorio y no puede estar vacío ni ser nulo.")
    private String nombre;

    @NotBlank(message = "El tipo de archivo es obligatorio y no puede estar vacío ni ser nulo.")
    private String tipoArchivo;

    @NotNull(message = "El archivo es obligatorio y no puede ser nulo.")
    @Size(min = 1, message = "El archivo es obligatorio y no puede quedar vacío.")
    @Lob
    private byte[] archivo;
}
