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
public class BT_TM_MASTER {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column
    private int ID_Master;

    @Column
    private String DE_DESCRIPCION;

    @Column
    private String STRING_UNO;

    @Column
    private String STRING_DOS;

    @Column
    private String STRING_TRES;

    @Column
    private int ID_UNO;

    @Column
    private int ID_DOS;

    @Column
    private int ID_TRES;

}
