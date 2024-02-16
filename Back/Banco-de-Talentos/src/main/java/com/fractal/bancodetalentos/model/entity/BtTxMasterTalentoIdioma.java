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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MASTER_TALENTO_IDIOMA")
    private Integer idMasterTalentoIdioma;

    @Column(name = "ID_TALENTO")
    private Integer idTalento;

    @Column(name = "ID_IDIOMA")
    private Integer idIdioma;

    @Column(name = "ID_NIVEL")
    private Integer idNivel;

    @Column(name = "NU_ESTRELLAS")
    private Integer nuEstrellas;

}
