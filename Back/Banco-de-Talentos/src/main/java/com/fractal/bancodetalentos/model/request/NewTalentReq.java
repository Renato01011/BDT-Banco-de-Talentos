package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewTalentReq {

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private byte[] fotoDePerfil;
    private List<Documento> documentos;
    private String descripcion;
    private String puestoActual;
    private String linkedin;
    private String github;
    private String tipoMoneda;
    private Integer montoInicial;
    private Integer montoFinal;
    private String celular;
    private List<HabilidadesTecnicas> habilidadesTecnicas;
    private List<HabilidadesBlandas> habilidadesBlandas;
    private List<ExperienciasLaborales> experienciasLaborales;
    private List<ExperienciasEducativas> experienciasEducativas;
    private List<Idiomas> idiomas;

}
