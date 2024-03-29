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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FEEDBACK")
    private Integer idFeedback;

    @Column(name = "ID_TALENTO")
    private Integer idTalento;

    @Column(name = "NU_ESTRELLAS")
    private Integer nuEstrellas;

    @Column(name = "DE_DESCRIPCION")
    private String deDescripcion;

}
