package com.fractal.bancodetalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BT_TX_MASTER_TALENTO")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTxMasterTalento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BT_ID_MASTER_TALENTO")
    @SequenceGenerator(name = "SEQ_BT_ID_MASTER_TALENTO", sequenceName = "SEQ_BT_ID_MASTER_TALENTO", allocationSize = 1)
    @Column(name = "ID_MASTER_TALENTO")
    private int idMasterTalento;

    @Column(name = "ID_TALENTO")
    private int idTalento;

    @Column(name = "ID_MASTER")
    private int idMaster;

    @Column(name = "ID")
    private int id;

}
