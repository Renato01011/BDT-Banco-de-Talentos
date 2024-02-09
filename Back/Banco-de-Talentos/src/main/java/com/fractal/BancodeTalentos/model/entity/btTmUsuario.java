package com.fractal.BancodeTalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "BT_TM_USUARIO")
@Getter
@Setter
@Data
@NoArgsConstructor
public class btTmUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO NUMBER")
    private int idUsuario;

    @Column(name = "NO_NOMBRE")
    private String noNombre;

    @Column(name = "AP_APELLIDO_PATERNO")
    private String apApellidoPaterno;

    @Column(name = "AP_APELLIDO_MATERNO")
    private String apApellidoMaterno;

    @Column(name = "IM_IMAGEN")
    private byte[] imImagen;

    @Column(name = "US_USUARIO")
    private String usUsuario;

    @Column(name = "PW_PASSWORD")
    private String pwPassword;
}
