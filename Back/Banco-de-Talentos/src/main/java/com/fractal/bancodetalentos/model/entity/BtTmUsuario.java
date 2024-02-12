package com.fractal.bancodetalentos.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BT_TM_USUARIO")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BtTmUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BT_ID_USUARIO_NUMBER")
    @SequenceGenerator(name = "SEQ_BT_ID_USUARIO_NUMBER", sequenceName = "SEQ_BT_ID_USUARIO_NUMBER", allocationSize = 1)
    @Column(name = "ID_USUARIO_NUMBER")
    private Integer idUsuario;

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
