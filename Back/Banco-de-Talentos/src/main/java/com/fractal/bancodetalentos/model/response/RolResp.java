package com.fractal.bancodetalentos.model.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolResp {
    private Integer id;
    private String name;
    private String code;
}
