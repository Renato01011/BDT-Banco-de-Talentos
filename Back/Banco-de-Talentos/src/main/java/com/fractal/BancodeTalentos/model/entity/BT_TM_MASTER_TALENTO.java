package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.GeneratedReferenceTypeDelegate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class BT_TM_MASTER_TALENTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_MASTER_TALENTO;

    @Column
    private int ID_TALENTO;

    @Column
    private int ID;

}
