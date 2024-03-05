package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.ExperienciasEducativas;
import com.fractal.bancodetalentos.model.response.GeneralResp;

import java.util.Map;

public interface ExperienciaEducativaService {

    Map<String, String> addNewEducExp(ExperienciasEducativas educativas, Integer id);
    GeneralResp putEducExp(Integer idTalent, Integer idEducExp, ExperienciasEducativas experienciasEducativas);
}
