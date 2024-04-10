package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.AddLanguageReq;
import com.fractal.bancodetalentos.model.request.UpdateLanguageReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;

import java.util.Map;

public interface MasterTalentoIdiomaService {

    Map<String, String> addNewLanguage(AddLanguageReq idiomas, Integer id);
    GeneralResp putLangExp(Integer idTalent, Integer idTalentLang, UpdateLanguageReq idiomas);
    GeneralResp deleteTalentLang(Integer idTalent, Integer idTalentLang);
}
