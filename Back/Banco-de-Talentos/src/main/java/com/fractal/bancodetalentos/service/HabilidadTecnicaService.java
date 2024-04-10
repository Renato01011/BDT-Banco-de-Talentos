package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.AddHabTechReq;
import com.fractal.bancodetalentos.model.response.TecSkillsResp;

import java.util.List;
import java.util.Map;

public interface HabilidadTecnicaService {


    List<TecSkillsResp> getTecSkillsResp();

    Map<String, String> addNewTechSkill(AddHabTechReq tecnicas, Integer id);

}
