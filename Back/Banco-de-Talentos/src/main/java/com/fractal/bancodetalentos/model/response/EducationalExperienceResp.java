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
public class EducationalExperienceResp {
    private Integer idEducationalExperience;
    private String institution;
    private String major;
    private String degree;
    private Date initialDate;
    private Date finalDate;
}
