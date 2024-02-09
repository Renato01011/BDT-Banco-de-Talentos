package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "BT_TD_HABILIDAD_BLANDA")
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTdHabilidadBlanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HABILIDAD_BLANDA")
    private int idHabilidadBlanda;

    @Column(name = "NO_HABILIDAD")
    private String noHabilidad;

    @Column(name = "ID_TALENTO")
    private int idTalento;

}
