package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

//@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class BT_TD_EXPERIENCIA_LABORAL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_EXPERIENCIA_LABORAL;

    @Column
    private String NO_EXPERIENCIA_LABORAL;

    @Column
    private String NO_PUESTO;

    @Column
    private Date FE_INICIO;

    @Column
    private Date FE_FIN;

    @Column
    private int ID_TALENTO;

}
