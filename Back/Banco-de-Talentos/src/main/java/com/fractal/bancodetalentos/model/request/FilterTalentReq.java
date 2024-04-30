package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterTalentReq {

    @NotNull(message = "El nombre de las Habilidades es obligatorio y no puede ser nulo.")
    private String habilities;

    @NotNull(message = "El Id del Idioma es obligatorio y no puede ser nulo.")
    private String languageIds;

    @NotNull(message = "El Id del Nivele es obligatorio y no puede ser nulo.")
    private String levelIds;

    @NotNull(message = "El campo Nombre o Puesto es obligatorio y no puede ser nulo.")
    private String nameJobTitle;

    @NotNull(message = "El Id del Favorito es obligatorio y no puede ser nulo.")
    private String userListIds;

    @NotNull(message = "El Id del Usuario es obligatorio y no puede ser nulo.")
    private Integer userId;

    @NotNull(message = "El numero de pagina es obligatorio y no puede ser nulo.")
    private Integer pagina;


}
