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
public class btTxListaUsuarioTalento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idListaUsuarioDetalle;

    @Column
    private int idListaUsuario;

    @Column
    private int idTalento;

}
