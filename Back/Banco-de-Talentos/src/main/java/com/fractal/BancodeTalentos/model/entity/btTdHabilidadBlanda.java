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
public class btTdHabilidadBlanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHabilidadBlanda;

    @Column
    private String noHabilidad;

    @Column
    private int idTalento;

}
