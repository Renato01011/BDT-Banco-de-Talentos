package com.fractal.bancodetalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BT_TM_MASTER")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTmMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ID_MASTER")
    private Integer idMaster;

    @Column(name = "DE_DESCRIPCION")
    private String deDescripcion;

    @Column(name = "STRING_UNO")
    private String stringUno;

    @Column(name = "STRING_DOS")
    private String stringDos;

    @Column(name = "STRING_TRES")
    private String stringTres;

    @Column(name = "ID_UNO")
    private Integer idUno;

    @Column(name = "ID_DOS")
    private Integer idDos;

    @Column(name = "ID_TRES")
    private Integer idTres;

}
