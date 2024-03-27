package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {
    @NotBlank(message = "El Username es obligatorio y no puede estar vacío ni ser nulo.")
    private String name;
}
