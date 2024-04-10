package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.AddFeedbackReq;

import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.FeedbackTalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service
@RequiredArgsConstructor
public class FeedbackTalentoServiceImpl implements FeedbackTalentoService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public GeneralResp addTalentFeedback(AddFeedbackReq feedback, Integer id) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }

        StoredProcedureQuery storedProcedureQueryNewFeedback = entityManager
                .createStoredProcedureQuery("SP_ADD_FEEDBACK")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, feedback.getNuEstrellas())
                .setParameter(3, feedback.getDescripcion())
                .setParameter(4, feedback.getUserFromId());
        storedProcedureQueryNewFeedback.execute();
        GeneralResp resp = new GeneralResp();
        resp.setCode(200);
        resp.setMessage("Correctly Added");

        return resp;

    }
}
