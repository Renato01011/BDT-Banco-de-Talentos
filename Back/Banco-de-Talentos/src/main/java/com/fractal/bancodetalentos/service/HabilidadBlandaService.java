package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.AddSoftSkillReq;

import java.util.Map;

public interface HabilidadBlandaService {

    Map<String, String> addNewSoftSkill(AddSoftSkillReq blanda, Integer id);
}
