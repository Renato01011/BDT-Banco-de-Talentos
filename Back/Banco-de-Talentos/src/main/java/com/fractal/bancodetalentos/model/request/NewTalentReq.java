package com.fractal.bancodetalentos.model.request;

import com.fractal.bancodetalentos.model.dto.*;
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
    private List<DocumentoDTO> documentos;
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
    @NotNull(message = "El campo Tipo de moneda es obligatorio y no puede ser nulo.")
    private Integer montoInicialPlanilla;
    @NotNull(message = "El campo Tipo de moneda es obligatorio y no puede ser nulo.")
    private Integer montoFinalPlanilla;
    @NotNull(message = "El campo Tipo de moneda es obligatorio y no puede ser nulo.")
    private Integer montoInicialRxH;
    @NotNull(message = "El campo Tipo de moneda es obligatorio y no puede ser nulo.")
    private Integer montoFinalRxh;
    @NotBlank(message = "El Número de celular es obligatorio y no puede estar vacío ni ser nulo.")
    private String celular;
    @Valid
    @NotEmpty(message = "La lista de Habilidades técnicas es un campo obligatorio y no puede estar vacía.")
    private List<HabilidadesTecnicasDTO> habilidadesTecnicas;
    @Valid
    @NotEmpty(message = "La lista de Habilidades blandas es un campo obligatorio y no puede estar vacía.")
    private List<HabilidadesBlandasDTO> habilidadesBlandas;
    @Valid
    @NotEmpty(message = "La lista de Experiencia laboral es un campo obligatorio y no puede estar vacía.")
    private List<ExperienciasLaboralesDTO> experienciasLaborales;
    @Valid
    @NotEmpty(message = "La lista de Experiencia educativa es un campo obligatorio y no puede estar vacía.")
    private List<ExperienciasEducativasDTO> experienciasEducativas;
    @Valid
    @NotEmpty(message = "La lista de Idiomas es un campo obligatorio y no puede estar vacía.")
    private List<IdiomasDTO> idiomas;
    @NotBlank(message = "La disponibilidad del talento es obligatorio y no puede estar vacío ni ser nulo.")
    private String disponibilidad;
    @Email(message = "El correo electrónico introducido no tiene el formato correcto.")
    @NotBlank(message = "El Email del talento es obligatorio y no puede estar vacío ni ser nulo.")
    private String email;
}
