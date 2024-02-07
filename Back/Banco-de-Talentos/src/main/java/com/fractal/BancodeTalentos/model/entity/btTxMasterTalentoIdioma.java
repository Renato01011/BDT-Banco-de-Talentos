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
public class btTxMasterTalentoIdioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMasterTalentoIdioma;

    @Column
    private int idTalento;

    @Column
    private int idIdioma;

    @Column
    private int idNivel;

    @Column
    private int nuEstrellas;

}
