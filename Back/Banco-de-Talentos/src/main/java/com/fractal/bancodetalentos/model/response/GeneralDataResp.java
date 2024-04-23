package com.fractal.bancodetalentos.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralDataResp {
    private List<LanguageResp> languages;
    private List<CurrenciesResp> currencies;
    private List<LangProficiencyResp> proficiency;
    private List<CountryResp> countries;
    private List<TecSkillsResp> skills;
}
