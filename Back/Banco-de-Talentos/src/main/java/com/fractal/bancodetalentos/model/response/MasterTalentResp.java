package com.fractal.bancodetalentos.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterTalentResp {
    private Integer idMasterTalent;
    private Integer idMaster;
    private Integer id;
    private String name;
    private String description;
    private String code;
    private String secondCode;
}
