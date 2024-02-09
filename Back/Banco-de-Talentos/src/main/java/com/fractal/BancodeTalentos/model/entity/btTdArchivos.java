package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "BT_TD_ARCHIVOS")
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTdArchivos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ARCHIVO")
    private int idArchivo;

    @Column(name = "NO_ARCHIVO")
    private String noArchivo;

    @Column(name = "FL_TIPO_ARCHIVO")
    private String flTipoArchivo;

    @Column(name = "AR_RCHIVO")
    private byte[] arArchivo;

    @Column(name = "ID_TALENTO")
    private int idTalento;

}
