package com.fractal.bancodetalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BT_TD_FEEDBACK_TALENTO")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTdFeedbackTalento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BT_ID_FEEDBACK")
    @SequenceGenerator(name = "SEQ_BT_ID_FEEDBACK", sequenceName = "SEQ_BT_ID_FEEDBACK", allocationSize = 1)
    @Column(name = "ID_FEEDBACK")
    private int idFeedback;

    @Column(name = "ID_TALENTO")
    private int idTalento;

    @Column(name = "NU_ESTRELLAS")
    private int nuEstrellas;

    @Column(name = "DE_DESCRIPCION")
    private String deDescripcion;

}
