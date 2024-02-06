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
public class BT_TM_TALENTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_TALENTO;

    @Column
    private String NO_NOMBRE;

    @Column
    private String AP_APELLIDO_PATERNO;

    @Column
    private String AP_APELLIDO_MATERNO;

    @Column
    private Blob IM_IMAGEN;

    @Column
    private String DE_DESCRIPCION;

    @Column
    private int NU_MONTO_INICIAL;

    @Column
    private int NU_MONTO_FINAL;

    @Column
    private String NU_CELULAR;

    @Column
    private String DI_LINKDN;

    @Column
    private String DI_GITHUB;

    @Column
    private Blob CV_BASE64;

    @Column
    private Date FE_CREACION;

}
