package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.AddWorkExpReq;
import com.fractal.bancodetalentos.model.request.UpdateWorkExpReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;

import java.util.Map;

public interface ExperienciaLaboralService {

    Map<String, String> addNewWorkExp(AddWorkExpReq laborales, Integer id);
    GeneralResp putWorkExp(Integer idTalent, Integer idWorkExp, UpdateWorkExpReq experienciasLaboralesDTO);
    GeneralResp deleteWorkExp(Integer idTalent, Integer idWorkExp);
}
