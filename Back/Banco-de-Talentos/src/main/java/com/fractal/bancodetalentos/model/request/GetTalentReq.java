package com.fractal.bancodetalentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTalentReq {
    @NotNull(message = "El Id del Usuario es obligatorio y no puede ser nulo.")
    private Integer userId;
}
