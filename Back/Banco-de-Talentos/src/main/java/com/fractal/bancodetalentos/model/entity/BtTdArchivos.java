package com.fractal.bancodetalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BT_TD_ARCHIVOS")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTdArchivos implements Serializable {

    @Id
    @Column(name = "ID_ARCHIVO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArchivo;

    @Column(name = "NO_ARCHIVO")
    private String noArchivo;

    @Column(name = "FL_TIPO_ARCHIVO")
    private String flTipoArchivo;

    @Column(name = "AR_RCHIVO")
    private byte[] arArchivo;

    @Column(name = "ID_TALENTO")
    private Integer idTalento;

}
