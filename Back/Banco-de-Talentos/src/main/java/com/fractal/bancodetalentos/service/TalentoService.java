package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.NewTalentReq;
import com.fractal.bancodetalentos.model.response.PostResp;

public interface TalentoService {

    PostResp addNewTalent(NewTalentReq newTalentRequest);

}
