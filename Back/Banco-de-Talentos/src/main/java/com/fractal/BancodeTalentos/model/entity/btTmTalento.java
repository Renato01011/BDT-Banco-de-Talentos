package com.fractal.BancodeTalentos.model.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.sql.Date;

@Entity
@Table(name = "BT_TM_TALENTO")
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTmTalento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BT_ID_TALENTO")
    @SequenceGenerator(name = "SEQ_BT_ID_TALENTO", sequenceName = "SEQ_BT_ID_TALENTO", allocationSize = 1)
    @Column(name = "ID_TALENTO")
    private int idTalento;

    @Column(name = "NO_NOMBRE")
    private String noNombre;

    @Column(name = "AP_APELLIDO_PATERNO")
    private String apApellidoPaterno;

    @Column(name = "AP_APELLIDO_MATERNO")
    private String apApellidoMaterno;

    @Column(name = "IM_IMAGEN")
    private byte[] imImagen;

    @Column(name = "DE_DESCRIPCION")
    private String deDescripcion;

    @Column(name = "NU_MONTO_INICIAL")
    private int nuMontoInicial;

    @Column(name = "NU_MONTO_FINAL")
    private int nuMontoFinal;

    @Column(name = "NU_CELULAR")
    private String nuCelular;

    @Column(name = "DI_LINKDN")
    private String diLinkdn;

    @Column(name = "DI_GITHUB")
    private String diGithub;

    @Column(name = "FE_CREACION")
    private Date feCreacion;

}
