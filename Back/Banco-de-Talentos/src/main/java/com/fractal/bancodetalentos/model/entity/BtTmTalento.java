package com.fractal.bancodetalentos.model.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "BT_TM_TALENTO")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTmTalento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TALENTO")
    private Integer idTalento;

    @Column(name = "NO_NOMBRE")
    private String noNombre;

    @Column(name = "AP_APELLIDO_PATERNO")
    private String apApellidoPaterno;

    @Column(name = "AP_APELLIDO_MATERNO")
    private String apApellidoMaterno;

    @Lob
    @Column(name = "IM_IMAGEN")
    private byte[] imImagen;

    @Column(name = "DE_DESCRIPCION")
    private String deDescripcion;

    @Column(name = "NU_MONTO_INICIAL")
    private Integer nuMontoInicial;

    @Column(name = "NU_MONTO_FINAL")
    private Integer nuMontoFinal;

    @Column(name = "NU_CELULAR")
    private String nuCelular;

    @Column(name = "DI_LINKDN")
    private String diLinkdn;

    @Column(name = "DI_GITHUB")
    private String diGithub;

    @Column(name = "FE_CREACION")
    private Date feCreacion;

    @Column(name = "CE_EMAIL")
    private String ceEmail;

    @Column(name = "DE_FUNCTIONS")
    private String deFunctions;

    @Column(name = "DE_PERFIL")
    private String dePerfil;


}
