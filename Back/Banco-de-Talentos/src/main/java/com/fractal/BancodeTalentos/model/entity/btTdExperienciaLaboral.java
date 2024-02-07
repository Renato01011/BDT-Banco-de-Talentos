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
public class btTdExperienciaLaboral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idExperienciaLaboral;

    @Column
    private String noExperienciaLaboral;

    @Column
    private String noPuesto;

    @Column
    private Date feInicio;

    @Column
    private Date feFin;

    @Column
    private int idTalento;

}
