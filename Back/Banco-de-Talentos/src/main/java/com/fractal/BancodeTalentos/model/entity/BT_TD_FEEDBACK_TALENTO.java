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
public class BT_TD_FEEDBACK_TALENTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_FEEDBACK;

    @Column
    private int ID_TALENTO;

    @Column
    private int NU_ESTRELLAS;

    @Column
    private String DE_DESCRIPCION;

}
