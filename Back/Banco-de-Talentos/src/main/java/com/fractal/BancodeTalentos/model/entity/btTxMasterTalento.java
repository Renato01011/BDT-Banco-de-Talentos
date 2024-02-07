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
public class btTxMasterTalento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMasterTalento;

    @Column
    private int idTalento;

    @Column
    private int id;

}
