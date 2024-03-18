package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.entity.BtTdFeedbackTalento;
import com.fractal.bancodetalentos.model.request.NewFeedbackReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;

public interface FeedbackTalentoService {

    GeneralResp addTalentFeedback(NewFeedbackReq newFeedbackReq, Integer id);
    void delete(BtTdFeedbackTalento feedbackTalento);

}
