package com.fractal.bancodetalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "BT_TD_EXPERIENCIA_LABORAL")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTdExperienciaLaboral implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BT_ID_EXPERIENCIA_LABORAL")
    @SequenceGenerator(name = "SEQ_BT_ID_EXPERIENCIA_LABORAL", sequenceName = "SEQ_BT_ID_EXPERIENCIA_LABORAL", allocationSize = 1)
    @Column(name = "ID_EXPERIENCIA_LABORAL")
    private int idExperienciaLaboral;

    @Column(name = "NO_EXPERIENCIA_LABORAL")
    private String noExperienciaLaboral;

    @Column(name = "NO_PUESTO")
    private String noPuesto;

    @Column(name = "FE_INICIO")
    private Date feInicio;

    @Column(name = "FE_FIN")
    private Date feFin;

    @Column(name = "ID_TALENTO")
    private int idTalento;

}
