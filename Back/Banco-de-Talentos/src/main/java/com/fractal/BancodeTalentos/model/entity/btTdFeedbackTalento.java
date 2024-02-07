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
public class btTdFeedbackTalento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFeedback;

    @Column
    private int idTalento;

    @Column
    private int nuEstrellas;

    @Column
    private String deDescripcion;

}
