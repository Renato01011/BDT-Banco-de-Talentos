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
public class SocialLinksReq {
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String linkedin;
    @NotBlank(message = "Este campo no puede estar vacío ni ser nulo")
    private String github;
}
