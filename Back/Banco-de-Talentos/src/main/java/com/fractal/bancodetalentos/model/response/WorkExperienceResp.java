package com.fractal.bancodetalentos.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceResp {
    private Integer idWorkExperience;
    private String firm;
    private String jobTitle;
    private Date intialDate;
    private Date finalDate;
    private Integer flActualidad;
}
