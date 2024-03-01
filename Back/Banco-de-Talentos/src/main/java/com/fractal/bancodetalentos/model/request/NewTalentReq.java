package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewTalentReq {

    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String nombre;
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String apellidoPaterno;
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String apellidoMaterno;

    @NotNull(message = "Este campo no puede ser nulo")
    @Size(min = 1, message = "Este campo no puede estar vacío")
    @Lob
    private byte[] fotoDePerfil;

    private List<Documento> documentos;
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String descripcion;
    @NotNull(message = "Este campo no puede ser nulo")
    private Integer idPuestoActual;
    @NotNull(message = "Este campo no puede ser nulo")
    private Integer idPais;
    @NotNull(message = "Este campo no puede ser nulo")
    private Integer idCiudad;
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String linkedin;
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String github;
    @NotNull(message = "Este campo no puede ser nulo")
    private Integer idTipoMoneda;
    @NotNull(message = "Este campo no puede ser nulo")
    @Min(value = 0,  message = "Debe ser un numero positivo o cero")
    private Integer montoInicial;
    @NotNull(message = "Este campo no puede ser nulo")
    @Min(value = 0,  message = "Debe ser un numero positivo o cero")
    private Integer montoFinal;
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String celular;

    private List<HabilidadesTecnicas> habilidadesTecnicas;

    private List<HabilidadesBlandas> habilidadesBlandas;

    private List<ExperienciasLaborales> experienciasLaborales;

    private List<ExperienciasEducativas> experienciasEducativas;

    private List<Idiomas> idiomas;

}
