package com.fractal.bancodetalentos.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LanguageLevelResp {
    private Integer idTalentLanguage;
    private Integer idLanguage;
    private String languageName;
    private String languageCode;
    private Integer idProficiency;
    private String proficiency;
    private Integer starCount;
}
