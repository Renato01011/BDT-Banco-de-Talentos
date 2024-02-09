package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "BT_TM_MASTER")
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTmMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID NUMBER")
    private int id;

    @Column(name = "ID_MASTER")
    private int idMaster;

    @Column(name = "DE_DESCRIPCION")
    private String deDescripcion;

    @Column(name = "STRING_UNO")
    private String stringUno;

    @Column(name = "STRING_DOS")
    private String stringDos;

    @Column(name = "STRING_TRES")
    private String stringTres;

    @Column(name = "ID_UNO")
    private int idUno;

    @Column(name = "ID_DOS")
    private int idDos;

    @Column(name = "ID_TRES")
    private int IdTres;

}
