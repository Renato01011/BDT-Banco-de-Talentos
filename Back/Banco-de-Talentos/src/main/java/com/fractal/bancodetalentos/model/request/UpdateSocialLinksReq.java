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
public class UpdateSocialLinksReq {
    @NotBlank(message = "El campo Linkedin es obligatorio y no puede estar vacío ni ser nulo.")
    private String linkedin;
    @NotBlank(message = "El campo Github es obligatorio y no puede estar vacío ni ser nulo.")
    private String github;
    
    @NotNull(message = "El Id del Talento es obligatorio y no puede ser nulo.")
    private Integer id;
}
