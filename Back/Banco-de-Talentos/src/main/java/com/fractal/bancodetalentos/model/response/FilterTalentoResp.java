package com.fractal.bancodetalentos.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterTalentoResp {
    private Integer id;
    private String name;
    private Integer initialSalary;
    private Integer finalSalary;
    private String location;
    private Integer avgRating;
}
