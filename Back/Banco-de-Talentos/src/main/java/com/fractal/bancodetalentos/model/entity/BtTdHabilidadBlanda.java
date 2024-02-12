package com.fractal.bancodetalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BT_TD_HABILIDAD_BLANDA")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTdHabilidadBlanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BT_ID_HABILIDAD_BLANDA")
    @SequenceGenerator(name = "SEQ_BT_ID_HABILIDAD_BLANDA", sequenceName = "SEQ_BT_ID_HABILIDAD_BLANDA", allocationSize = 1)
    @Column(name = "ID_HABILIDAD_BLANDA")
    private int idHabilidadBlanda;

    @Column(name = "NO_HABILIDAD")
    private String noHabilidad;

    @Column(name = "ID_TALENTO")
    private int idTalento;

}
