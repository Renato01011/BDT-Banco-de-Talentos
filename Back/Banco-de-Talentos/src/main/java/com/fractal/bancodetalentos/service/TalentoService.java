package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.NewTalentReq;
import com.fractal.bancodetalentos.model.response.PostResp;
import com.fractal.bancodetalentos.model.response.TalentResp;

public interface TalentoService {

    PostResp addNewTalent(NewTalentReq newTalentRequest);

    TalentResp getTalent(Integer id);

}
