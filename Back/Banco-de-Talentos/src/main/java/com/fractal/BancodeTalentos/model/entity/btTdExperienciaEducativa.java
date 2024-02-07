package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTdExperienciaEducativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idExperienciaEducativa;

    @Column
    private String noInstitucionEducativa;

    @Column
    private String noCarrera;

    @Column
    private String noGrado;

    @Column
    private Date feInicio;

    @Column
    private Date feFin;

    @Column
    private int idTalento;

}
