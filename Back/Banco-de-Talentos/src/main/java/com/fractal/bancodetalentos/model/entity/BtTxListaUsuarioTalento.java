package com.fractal.bancodetalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BT_TX_LISTA_USUARIO_TALENTO")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTxListaUsuarioTalento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BT_ID_LISTA_USUARIO_DETALLE")
    @SequenceGenerator(name = "SEQ_BT_ID_LISTA_USUARIO_DETALLE", sequenceName = "SEQ_BT_ID_LISTA_USUARIO_DETALLE", allocationSize = 1)
    @Column(name = "ID_LISTA_USUARIO_DETALLE")
    private Integer idListaUsuarioDetalle;

    @Column(name = "ID_LISTA_USUARIO")
    private Integer idListaUsuario;

    @Column(name = "ID_TALENTO")
    private Integer idTalento;

}
