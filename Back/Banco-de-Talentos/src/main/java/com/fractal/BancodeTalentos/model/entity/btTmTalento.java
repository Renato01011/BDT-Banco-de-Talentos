package com.fractal.BancodeTalentos.model.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.sql.Date;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTmTalento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTalento;

    @Column
    private String noNombre;

    @Column
    private String apApellidoPaterno;

    @Column
    private String apApellidoMaterno;

    @Column
    private byte[] imImagen;

    @Column
    private String deDescripcion;

    @Column
    private int nuMontoInicial;

    @Column
    private int nuMontoFinal;

    @Column
    private String nuCelular;

    @Column
    private String diLinkdn;

    @Column
    private String diGithub;

    @Column
    private Date feCreacion;

}
