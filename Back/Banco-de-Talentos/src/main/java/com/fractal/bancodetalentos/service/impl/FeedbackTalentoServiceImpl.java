package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.dto.TmUsuarioDTO;
import com.fractal.bancodetalentos.model.entity.BtTdFeedbackTalento;
import com.fractal.bancodetalentos.model.request.NewFeedbackReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.FeedbackTalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FeedbackTalentoServiceImpl implements FeedbackTalentoService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public GeneralResp addTalentFeedback(NewFeedbackReq newFeedbackReq, Integer id) {

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
                .setParameter(2, newFeedbackReq.getNuEstrellas())
                .setParameter(3, newFeedbackReq.getDescripcion())
                .setParameter(4, newFeedbackReq.getUserFromId());
        storedProcedureQueryNewFeedback.execute();
        GeneralResp resp = new GeneralResp();
        resp.setCode(200);
        resp.setMessage("Correctly Added");

        return resp;

    }

    @Override
    public void delete(BtTdFeedbackTalento feedbackTalento) {

    }
}
