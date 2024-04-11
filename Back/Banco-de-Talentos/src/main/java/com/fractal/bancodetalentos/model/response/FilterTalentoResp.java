package com.fractal.bancodetalentos.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterTalentoResp {

    private Integer id;
    @Lob
    private byte[] image;
    private String nameJobTitle;
    private Integer initialSalaryPlanilla;
    private Integer finalSalaryPlanilla;
    private Integer initialSalaryRxH;
    private Integer finalSalaryRxH;
    private String location;
    private Integer avgRating;
    private Integer inUserList;

}
