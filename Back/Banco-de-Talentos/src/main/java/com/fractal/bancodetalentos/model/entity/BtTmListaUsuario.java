package com.fractal.bancodetalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "BT_TM_LISTA_USUARIO")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTmListaUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LISTA_USUARIO")
    private Integer idListaUsuario;

    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Column(name = "NO_LISTA_USUARIO")
    private String noListaUsuario;

    @Column(name = "FE_CREACION")
    private Date feCreacion;
}
