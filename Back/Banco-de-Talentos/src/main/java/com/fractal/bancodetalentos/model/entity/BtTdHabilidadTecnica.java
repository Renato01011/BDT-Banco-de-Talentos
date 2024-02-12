package com.fractal.bancodetalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BT_TD_HABILIDAD_TECNICA")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTdHabilidadTecnica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BT_ID_HABILIDAD_TECNICA")
    @SequenceGenerator(name = "SEQ_BT_ID_HABILIDAD_TECNICA", sequenceName = "SEQ_BT_ID_HABILIDAD_TECNICA", allocationSize = 1)
    @Column(name = "ID_HABILIDAD_TECNICA")
    private Integer idHabilidadTecnica;

    @Column(name = "NO_HABILIDAD")
    private String noHabilidad;

    @Column(name = "ID_TALENTO")
    private Integer idTalento;

    @Column(name = "NU_ANIOS")
    private Integer nuAnios;

}
