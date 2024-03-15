package com.fractal.bancodetalentos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TxRolDTO {

    private Integer idUsuario;
    private Integer idRol;
    private String name;
}
