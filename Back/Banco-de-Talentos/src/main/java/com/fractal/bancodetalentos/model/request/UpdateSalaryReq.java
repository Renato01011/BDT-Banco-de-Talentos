package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSalaryReq {
    @NotNull(message = "El Tipo de moneda es obligatorio y no puede ser nulo.")
    private Integer idCoin;
    @NotNull(message = "El Monto inicial es obligatorio y no puede ser nulo.")
    @Min(value = 0,  message = "El Monto inicial debe ser un numero positivo o cero.")
    private Integer initialSalary;
    @NotNull(message = "El Monto final es obligatorio y no puede ser nulo.")
    @Min(value = 0,  message = "El Monto final debe ser un numero positivo o cero.")
    private Integer finalSalary;

    @NotNull(message = "El Id del Talento es obligatorio y no puede ser nulo.")
    private Integer id;
}
