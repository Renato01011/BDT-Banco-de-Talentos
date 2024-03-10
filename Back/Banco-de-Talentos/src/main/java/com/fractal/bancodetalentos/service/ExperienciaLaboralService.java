package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.ExperienciasLaborales;
import com.fractal.bancodetalentos.model.response.GeneralResp;

import java.util.Map;

public interface ExperienciaLaboralService {

    Map<String, String> addNewWorkExp(ExperienciasLaborales laborales, Integer id);
    GeneralResp putWorkExp(Integer idTalent, Integer idWorkExp, ExperienciasLaborales experienciasLaborales);
    GeneralResp deleteWorkExp(Integer idTalent, Integer idWorkExp);
}
