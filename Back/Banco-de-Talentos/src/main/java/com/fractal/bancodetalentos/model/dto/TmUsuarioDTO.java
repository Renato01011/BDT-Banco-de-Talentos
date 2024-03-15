package com.fractal.bancodetalentos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TmUsuarioDTO {

    private Integer idUsuario;
    private String noNombre;
    private String apApellidoPaterno;
    private String apApellidoMaterno;
    @Lob
    private byte[] imImagen;
    private String usUsuario;
    private String pwPassword;
    private List<TxRolDTO> roles;
}
