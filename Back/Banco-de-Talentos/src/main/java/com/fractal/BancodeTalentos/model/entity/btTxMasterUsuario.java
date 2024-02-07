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
public class btTxMasterUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMasterUsuario;

    @Column
    private int idUsuario;

    @Column
    private int idRol;
}
