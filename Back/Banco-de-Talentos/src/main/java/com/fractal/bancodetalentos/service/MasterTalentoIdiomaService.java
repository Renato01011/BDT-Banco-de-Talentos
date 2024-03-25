package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.Idiomas;
import com.fractal.bancodetalentos.model.response.GeneralResp;

import java.util.Map;

public interface MasterTalentoIdiomaService {

    Map<String, String> addNewLanguage(Idiomas idiomas, Integer id);
    GeneralResp putLangExp(Integer idTalent, Integer idTalentLang, Idiomas idiomas);
    GeneralResp deleteTalentLang(Integer idTalent, Integer idTalentLang);
}
