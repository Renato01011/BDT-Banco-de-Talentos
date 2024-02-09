package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "BT_TX_MASTER_USUARIO")
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTxMasterUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BT_ID_MASTER_USUARIO")
    @SequenceGenerator(name = "SEQ_BT_ID_MASTER_USUARIO", sequenceName = "SEQ_BT_ID_MASTER_USUARIO", allocationSize = 1)
    @Column(name = "ID_MASTER_USUARIO")
    private int idMasterUsuario;

    @Column(name = "ID_USUARIO")
    private int idUsuario;

    @Column(name = "ID_ROL")
    private int idRol;
}
