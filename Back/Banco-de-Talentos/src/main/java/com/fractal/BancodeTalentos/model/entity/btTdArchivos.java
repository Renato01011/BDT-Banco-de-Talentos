package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTdArchivos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArchivo;

    @Column
    private String noArchivo;

    @Column
    private String flTipoArchivo;

    @Column
    private byte[] arArchivo;

    @Column
    private int idTalento;

}
