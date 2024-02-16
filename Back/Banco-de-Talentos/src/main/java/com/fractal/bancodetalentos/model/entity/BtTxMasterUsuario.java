package com.fractal.bancodetalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BT_TX_MASTER_USUARIO")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTxMasterUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MASTER_USUARIO")
    private Integer idMasterUsuario;

    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Column(name = "ID_ROL")
    private Integer idRol;
}
