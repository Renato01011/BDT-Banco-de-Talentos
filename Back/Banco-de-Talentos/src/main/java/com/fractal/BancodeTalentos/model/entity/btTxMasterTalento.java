package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "BT_TX_MASTER_TALENTO")
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTxMasterTalento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MASTER_TALENTO")
    private int idMasterTalento;

    @Column(name = "ID_TALENTO")
    private int idTalento;

    @Column(name = "ID_MASTER")
    private int idMaster;

    @Column(name = "ID")
    private int id;

}
