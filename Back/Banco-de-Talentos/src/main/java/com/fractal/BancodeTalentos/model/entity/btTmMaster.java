package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTmMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int idMaster;

    @Column
    private String deDescripcion;

    @Column
    private String stringUno;

    @Column
    private String stringDos;

    @Column
    private String stringTres;

    @Column
    private int idUno;

    @Column
    private int idDos;

    @Column
    private int IdTres;

}
