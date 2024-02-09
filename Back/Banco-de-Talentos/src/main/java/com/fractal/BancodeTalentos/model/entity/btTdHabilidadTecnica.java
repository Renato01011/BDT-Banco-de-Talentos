package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "BT_TD_HABILIDAD_TECNICA")
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTdHabilidadTecnica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HABILIDAD_TECNICA")
    private int idHabilidadTecnica;

    @Column(name = "NO_HABILIDAD")
    private String noHabilidad;

    @Column(name = "ID_TALENTO")
    private int idTalento;

    @Column(name = "NU_ANIOS")
    private int nuAnios;

}
