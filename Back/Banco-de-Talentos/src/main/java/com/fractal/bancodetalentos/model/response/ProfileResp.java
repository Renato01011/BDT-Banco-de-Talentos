package com.fractal.bancodetalentos.model.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResp {
    private BigDecimal id;
    private String name;
    private String code;
}
