package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTmListaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idListaUsuario;

    @Column
    private int idUsuario;

    @Column
    private String noListaUsuario;

    @Column
    private Date feCreacion;
}
