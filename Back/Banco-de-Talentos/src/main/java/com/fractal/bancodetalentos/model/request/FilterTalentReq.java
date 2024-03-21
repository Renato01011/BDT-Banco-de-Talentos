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

    @NotNull(message = "Este campo no puede ser nulo")
    private String habilities;

    @NotNull(message = "Este campo no puede ser nulo")
    private String languageIds;

    @NotNull(message = "Este campo no puede ser nulo")
    private String levelIds;

    @NotNull(message = "Este campo no puede ser nulo")
    private String nameJobTitle;

    @NotNull(message = "Este campo no puede ser nulo")
    private String userListIds;

    @NotNull(message = "Este campo no puede ser nulo")
    private Integer userId;

}
