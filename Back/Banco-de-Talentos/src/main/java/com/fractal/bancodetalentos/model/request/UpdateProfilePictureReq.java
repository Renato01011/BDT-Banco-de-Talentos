package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfilePictureReq {
    @NotNull(message = "La Foto de perfil es obligatorio y no puede ser nulo.")
    @Size(min = 1, message = "La Foto de perfil es obligatorio y no puede estar vacío.")
    @Lob
    private byte[] profilePicture;

    @NotNull(message = "El Id del Talento es obligatorio y no puede ser nulo.")
    private Integer id;
}
