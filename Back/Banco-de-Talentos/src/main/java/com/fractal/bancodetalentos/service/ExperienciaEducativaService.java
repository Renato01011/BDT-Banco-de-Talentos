package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.AddEducExpReq;
import com.fractal.bancodetalentos.model.request.UpdateEducExpReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;

import java.util.Map;

public interface ExperienciaEducativaService {

    Map<String, String> addNewEducExp(AddEducExpReq educativas, Integer id);
    GeneralResp putEducExp(Integer idTalent, Integer idEducExp, UpdateEducExpReq educExpReq);
    GeneralResp deleteEducExp(Integer idTalent, Integer idEducExp);
}
