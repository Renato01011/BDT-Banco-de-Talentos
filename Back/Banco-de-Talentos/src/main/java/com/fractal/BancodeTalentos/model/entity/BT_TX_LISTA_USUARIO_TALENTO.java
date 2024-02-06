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
public class BT_TX_LISTA_USUARIO_TALENTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_LISTA_USUARIO_DETALLE;

    @Column
    private int ID_LISTA_USUARIO;

    @Column
    private int ID_TALENTO;

}
