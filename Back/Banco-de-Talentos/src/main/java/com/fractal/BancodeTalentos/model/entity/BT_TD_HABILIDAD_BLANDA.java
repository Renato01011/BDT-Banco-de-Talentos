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
public class BT_TD_HABILIDAD_BLANDA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_HABILIDAD_BLANDA;

    @Column
    private String NO_HABILIDAD;

    @Column
    private int ID_TALENTO;

}
