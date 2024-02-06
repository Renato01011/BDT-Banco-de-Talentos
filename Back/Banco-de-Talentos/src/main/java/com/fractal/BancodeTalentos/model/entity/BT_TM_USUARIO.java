package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class BT_TM_USUARIO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_USUARIO;

    @Column
    private String NO_NOMBRE;

    @Column
    private String AP_APELLIDO_PATERNO;

    @Column
    private String AP_APELLIDO_MATERNO;

    @Column
    private Blob IM_IMAGEN;

    @Column
    private String USUARIO;

    @Column
    private String PASSWORD;
}
