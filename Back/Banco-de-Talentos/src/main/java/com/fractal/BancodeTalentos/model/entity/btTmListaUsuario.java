package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "BT_TM_LISTA_USUARIO")
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTmListaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BT_ID_LISTA_USUARIO")
    @SequenceGenerator(name = "SEQ_BT_ID_LISTA_USUARIO", sequenceName = "SEQ_BT_ID_LISTA_USUARIO", allocationSize = 1)
    @Column(name = "ID_LISTA_USUARIO")
    private int idListaUsuario;

    @Column(name = "ID_USUARIO")
    private int idUsuario;

    @Column(name = "NO_LISTA_USUARIO")
    private String noListaUsuario;

    @Column(name = "FE_CREACION")
    private Date feCreacion;
}
