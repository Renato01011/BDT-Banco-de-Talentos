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
    private Integer idMasterTalento;

    @Column(name = "ID_TALENTO")
    private Integer idTalento;

    @Column(name = "ID_MASTER")
    private Integer idMaster;

    @Column(name = "ID")
    private Integer id;

}
