package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewTalentReq {

    @NotBlank(message = "El Nombre del talento es obligatorio y no puede estar vacío ni ser nulo.")
    private String nombre;
    @NotBlank(message = "El Apellido paterno del talento es obligatorio y no puede estar vacío ni ser nulo.")
    private String apellidoPaterno;
    @NotBlank(message = "El Apellido materno del talento es obligatorio y no puede estar vacío ni ser nulo.")
    private String apellidoMaterno;

    @NotNull(message = "La Foto de perfil es obligatorio y no puede ser nulo.")
    @Size(min = 1, message = "La Foto de perfil es obligatorio y no puede estar vacío.")
    @Lob
    private byte[] fotoDePerfil;
    @Valid
    @NotEmpty(message = "La lista de Documentos es un campo obligatorio y no puede estar vacía.")
    private List<Documento> documentos;
    @NotBlank(message = "El campo Descripción es obligatorio y no puede estar vacío ni ser nulo.")
    private String descripcion;
    @NotNull(message = "El campo Puesto es obligatorio y no puede ser nulo.")
    private Integer idPuestoActual;
    @NotNull(message = "El campo Pais es obligatorio y no puede ser nulo.")
    private Integer idPais;
    @NotNull(message = "El campo Ciudad es obligatorio y no puede ser nulo.")
    private Integer idCiudad;
    @NotBlank(message = "El campo Linkedin es obligatorio y no puede estar vacío ni ser nulo.")
    private String linkedin;
    @NotBlank(message = "El campo Github es obligatorio y no puede estar vacío ni ser nulo.")
    private String github;
    @NotNull(message = "El campo Tipo de moneda es obligatorio y no puede ser nulo.")
    private Integer idTipoMoneda;
    @NotNull(message = "El Monto inicial es obligatorio y no puede ser nulo.")
    @Min(value = 0,  message = "El Monto inicial debe ser un numero positivo o cero.")
    private Integer montoInicial;
    @NotNull(message = "El Monto final es obligatorio y no puede ser nulo.")
    @Min(value = 0,  message = "El Monto final debe ser un numero positivo o cero.")
    private Integer montoFinal;
    @NotBlank(message = "El Número de celular es obligatorio y no puede estar vacío ni ser nulo.")
    private String celular;
    @Valid
    @NotEmpty(message = "La lista de Habilidades técnicas es un campo obligatorio y no puede estar vacía.")
    private List<HabilidadesTecnicas> habilidadesTecnicas;
    @Valid
    @NotEmpty(message = "La lista de Habilidades blandas es un campo obligatorio y no puede estar vacía.")
    private List<HabilidadesBlandas> habilidadesBlandas;
    @Valid
    @NotEmpty(message = "La lista de Experiencia laboral es un campo obligatorio y no puede estar vacía.")
    private List<ExperienciasLaborales> experienciasLaborales;
    @Valid
    @NotEmpty(message = "La lista de Experiencia educativa es un campo obligatorio y no puede estar vacía.")
    private List<ExperienciasEducativas> experienciasEducativas;
    @Valid
    @NotEmpty(message = "La lista de Idiomas es un campo obligatorio y no puede estar vacía.")
    private List<Idiomas> idiomas;

}
