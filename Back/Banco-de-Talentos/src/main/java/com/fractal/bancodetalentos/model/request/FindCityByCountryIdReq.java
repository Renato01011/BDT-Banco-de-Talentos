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
public class FindCityByCountryIdReq {

    @NotNull(message = "El Id del Pais es obligatorio y no puede ser nulo.")
    private Integer idCountry;
}
