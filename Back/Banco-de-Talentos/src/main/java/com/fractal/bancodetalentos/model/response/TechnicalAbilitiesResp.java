package com.fractal.bancodetalentos.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TechnicalAbilitiesResp {
    private Integer idTechnicalAbility;
    private String name;
    private BigDecimal years;
}
