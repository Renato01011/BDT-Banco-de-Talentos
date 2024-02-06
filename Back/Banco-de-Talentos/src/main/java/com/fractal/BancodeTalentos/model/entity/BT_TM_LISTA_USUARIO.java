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
public class BT_TM_LISTA_USUARIO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_LISTA_USUARIO;

    @Column
    private int ID_USUARIO;

    @Column
    private int NO_LISTA_USUARIO;

    @Column
    private Date FE_CREACION;
}
