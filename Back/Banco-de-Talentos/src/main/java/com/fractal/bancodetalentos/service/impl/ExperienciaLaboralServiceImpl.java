package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.AddWorkExpReq;
import com.fractal.bancodetalentos.model.request.UpdateWorkExpReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.ExperienciaLaboralService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExperienciaLaboralServiceImpl implements ExperienciaLaboralService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Map<String, String> addNewWorkExp(AddWorkExpReq laborales, Integer id) {
        boolean existsTalent = existsTalentId(id);
        if (!existsTalent) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }

        StoredProcedureQuery storedProcedureQueryExperienciasLaborales = entityManager
                .createStoredProcedureQuery("SP_ADD_WORK_EXPERIENCE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, laborales.getEmpresa())
                .setParameter(3, laborales.getPuesto())
                .setParameter(4, laborales.getFechaInicio())
                .setParameter(5, laborales.getFechaFin())
                .setParameter(6, laborales.getFlActualidad())
                .setParameter(7, laborales.getFunctions());
        storedProcedureQueryExperienciasLaborales.execute();
        Map<String, String> resp = new HashMap<>();
        resp.put("id", String.valueOf(id));
        resp.put("message", "CREATED");

        return resp;
    }

    @Override
    public GeneralResp putWorkExp(Integer idTalent, Integer idWorkExp, UpdateWorkExpReq workExpReq) {
        boolean existsTalent = existsTalentId(idTalent);
        if (!existsTalent) {
            throw new ResourceNotFoundException("Talent", "id", idTalent);
        }

        StoredProcedureQuery storedProcedureQueryExperienciasLaborales = entityManager
                .createStoredProcedureQuery("SP_EDIT_WORK_EXPERIENCE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                .setParameter(1, idWorkExp)
                .setParameter(2, workExpReq.getEmpresa())
                .setParameter(3, workExpReq.getPuesto())
                .setParameter(4, workExpReq.getFechaInicio())
                .setParameter(5, workExpReq.getFechaFin())
                .setParameter(6, workExpReq.getFlActualidad())
                .setParameter(7, workExpReq.getFunctions());
        storedProcedureQueryExperienciasLaborales.execute();

        GeneralResp generalResp = new GeneralResp();
        generalResp.setCode(200);
        generalResp.setMessage("Correctly Updated");

        return generalResp;
    }

    @Override
    public GeneralResp deleteWorkExp(Integer idTalent, Integer idWorkExp) {
        boolean existsTalent = existsTalentId(idTalent);
        if (!existsTalent) {
            throw new ResourceNotFoundException("Talent", "id", idTalent);
        }

        StoredProcedureQuery storedProcedureQueryExperienciasLaborales = entityManager
                .createStoredProcedureQuery("SP_DELETE_WORK_EXPERIENCE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, idWorkExp);
        storedProcedureQueryExperienciasLaborales.execute();

        GeneralResp generalResp = new GeneralResp();
        generalResp.setCode(200);
        generalResp.setMessage("Correctly Deleted");

        return generalResp;
    }

    private boolean existsTalentId(Integer id) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(2);
        return exists == 1;
    }
}
