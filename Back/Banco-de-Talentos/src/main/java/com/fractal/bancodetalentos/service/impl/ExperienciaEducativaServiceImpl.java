package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.entity.BtTdExperienciaEducativa;
import com.fractal.bancodetalentos.model.request.ExperienciasEducativas;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.ExperienciaEducativaService;
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
public class ExperienciaEducativaServiceImpl implements ExperienciaEducativaService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Map<String, String> addNewEducExp(ExperienciasEducativas educativas, Integer id) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }

        StoredProcedureQuery storedProcedureQueryExperienciasEducativas = entityManager
                .createStoredProcedureQuery("SP_ADD_EDUCATIONAL_EXPERIENCE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, educativas.getInstitucion())
                .setParameter(3, educativas.getCarrera())
                .setParameter(4, educativas.getGrado())
                .setParameter(5, educativas.getFechaInicio())
                .setParameter(6, educativas.getFechaFin())
                .setParameter(7, educativas.getFlActualidad());
        storedProcedureQueryExperienciasEducativas.execute();
        Map<String, String> resp = new HashMap<>();
        resp.put("id", String.valueOf(id));
        resp.put("message", "CREATED");

        return resp;
    }

    @Override
    public GeneralResp putEducExp(Integer idTalent, Integer idEducExp, ExperienciasEducativas experienciasEducativas) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, idTalent);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", idTalent);
        }

        StoredProcedureQuery storedProcedureQueryExperienciasEducativas = entityManager
                .createStoredProcedureQuery("SP_EDIT_EDUCATIONAL_EXPERIENCE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN)
                .setParameter(1, idEducExp)
                .setParameter(2, experienciasEducativas.getInstitucion())
                .setParameter(3, experienciasEducativas.getCarrera())
                .setParameter(4, experienciasEducativas.getGrado())
                .setParameter(5, experienciasEducativas.getFechaInicio())
                .setParameter(6, experienciasEducativas.getFechaFin())
                .setParameter(7, experienciasEducativas.getFlActualidad());
        storedProcedureQueryExperienciasEducativas.execute();

        GeneralResp generalResp = new GeneralResp();
        generalResp.setCode(200);
        generalResp.setMessage("Correctly Updated");

        return generalResp;

    }

    @Override
    public GeneralResp deleteEducExp(Integer idTalent, Integer idEducExp) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, idTalent);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", idTalent);
        }

        StoredProcedureQuery storedProcedureQueryExperienciasEducativas = entityManager
                .createStoredProcedureQuery("SP_DELETE_EDUCATIONAL_EXPERIENCE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, idEducExp);
        storedProcedureQueryExperienciasEducativas.execute();

        GeneralResp generalResp = new GeneralResp();
        generalResp.setCode(200);
        generalResp.setMessage("Correctly Deleted");

        return generalResp;

    }
}
