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

    @Lob
    private byte[] fotoDePerfil;
    private List<DocumentoDTO> documentos;
    @Size(max = 1000, message = "El límite máximo de caracteres para la descripcion es de 100.")
    private String descripcion;

    @Size(max = 50, message = "El límite máximo de caracteres para el puesto es de 50.")
    @NotNull(message = "El campo Puesto es obligatorio y no puede ser nulo.")
    private String puesto;

    @NotNull(message = "El campo Pais es obligatorio y no puede ser nulo.")
    private Integer idPais;
    @NotNull(message = "El campo Ciudad es obligatorio y no puede ser nulo.")
    private Integer idCiudad;
    private String linkedin;
    private String github;
    @NotNull(message = "El campo Tipo de moneda es obligatorio y no puede ser nulo.")
    private Integer idTipoMoneda;
    @NotNull(message = "El campo monto inicial planilla es obligatorio y no puede ser nulo.")
    private Integer montoInicialPlanilla;
    @NotNull(message = "El campo monto final planilla es obligatorio y no puede ser nulo.")
    private Integer montoFinalPlanilla;
    @NotNull(message = "El campo monto inicial recibos por honorarios es obligatorio y no puede ser nulo.")
    private Integer montoInicialRxH;
    @NotNull(message = "El campo monto final recibos por honorarios es obligatorio y no puede ser nulo.")
    private Integer montoFinalRxh;
    private String celular;
    @Valid
    @NotEmpty(message = "La lista de Habilidades técnicas es un campo obligatorio y no puede estar vacía.")
    private List<HabilidadesTecnicasDTO> habilidadesTecnicas;
    @Valid
    private List<HabilidadesBlandasDTO> habilidadesBlandas;
    @Valid
    private List<ExperienciasLaboralesDTO> experienciasLaborales;
    @Valid
    private List<ExperienciasEducativasDTO> experienciasEducativas;
    @Valid
    private List<IdiomasDTO> idiomas;
    @NotBlank(message = "La disponibilidad del talento es obligatorio y no puede estar vacío ni ser nulo.")
    private String disponibilidad;
    @Email(message = "El correo electrónico introducido no tiene el formato correcto.")
    private String email;
}
