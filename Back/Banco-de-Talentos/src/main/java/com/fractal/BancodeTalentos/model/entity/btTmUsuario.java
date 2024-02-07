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
public class btTmUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column
    private String noNombre;

    @Column
    private String apApellidoPaterno;

    @Column
    private String apApellidoMaterno;

    @Column
    private byte[] imImagen;

    @Column
    private String usUsuario;

    @Column
    private String pwPassword;
}
