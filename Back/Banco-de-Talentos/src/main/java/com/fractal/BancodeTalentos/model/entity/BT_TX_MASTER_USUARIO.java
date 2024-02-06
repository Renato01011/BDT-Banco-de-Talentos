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
public class BT_TX_MASTER_USUARIO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_MASTER_USUARIO;

    @Column
    private int ID_USUARIO;

    @Column
    private int ID;
}
