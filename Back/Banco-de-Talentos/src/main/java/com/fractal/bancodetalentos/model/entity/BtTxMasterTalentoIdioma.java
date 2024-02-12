package com.fractal.bancodetalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BT_TX_MASTER_TALENTO_IDIOMA")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTxMasterTalentoIdioma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BT_ID_MASTER_TALENTO_IDIOMA")
    @SequenceGenerator(name = "SEQ_BT_ID_MASTER_TALENTO_IDIOMA", sequenceName = "SEQ_BT_ID_MASTER_TALENTO_IDIOMA", allocationSize = 1)
    @Column(name = "ID_MASTER_TALENTO_IDIOMA")
    private int idMasterTalentoIdioma;

    @Column(name = "ID_TALENTO")
    private int idTalento;

    @Column(name = "ID_IDIOMA")
    private int idIdioma;

    @Column(name = "ID_NIVEL")
    private int idNivel;

    @Column(name = "NU_ESTRELLAS")
    private int nuEstrellas;

}
