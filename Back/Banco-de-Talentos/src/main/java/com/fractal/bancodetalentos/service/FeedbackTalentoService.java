package com.fractal.bancodetalentos.service;


import com.fractal.bancodetalentos.model.request.AddFeedbackReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;

public interface FeedbackTalentoService {

    GeneralResp addTalentFeedback(AddFeedbackReq feedback, Integer id);

}
