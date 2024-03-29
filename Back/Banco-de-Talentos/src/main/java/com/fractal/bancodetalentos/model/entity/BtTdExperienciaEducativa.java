package com.fractal.bancodetalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "BT_TD_EXPERIENCIA_EDUCATIVA")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTdExperienciaEducativa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EXPERIENCIA_EDUCATIVA")
    private Integer idExperienciaEducativa;

    @Column(name = "NO_INSTITUCION_EDUCATIVA")
    private String noInstitucionEducativa;

    @Column(name = "NO_CARRERA")
    private String noCarrera;

    @Column(name = "NO_GRADO")
    private String noGrado;

    @Column(name = "FE_INICIO")
    private Date feInicio;

    @Column(name = "FE_FIN")
    private Date feFin;

    @Column(name = "ID_TALENTO")
    private Integer idTalento;

}
