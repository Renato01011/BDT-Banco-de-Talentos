package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryReq {
    @NotNull(message = "Este campo no puede ser nulo")
    private Integer initialSalary;
    @NotNull(message = "Este campo no puede ser nulo")
    private Integer finalSalary;
}
