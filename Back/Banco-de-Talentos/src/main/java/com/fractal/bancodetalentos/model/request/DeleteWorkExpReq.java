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
public class DeleteWorkExpReq {

    @NotNull(message = "El Id de la Experiencia es obligatorio y no puede ser nulo.")
    private Integer idWorkExp;

    @NotNull(message = "El Id del Talento es obligatorio y no puede ser nulo.")
    private Integer idTalent;
}
