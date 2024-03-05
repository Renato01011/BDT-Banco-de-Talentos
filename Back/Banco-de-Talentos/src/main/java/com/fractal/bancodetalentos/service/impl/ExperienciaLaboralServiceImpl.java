package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.entity.BtTdExperienciaLaboral;
import com.fractal.bancodetalentos.model.request.ExperienciasLaborales;
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
    public Map<String, String> addNewWorkExp(ExperienciasLaborales laborales, Integer id) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }

        StoredProcedureQuery storedProcedureQueryExperienciasLaborales = entityManager
                .createStoredProcedureQuery("SP_ADD_WORK_EXPERIENCE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, Date.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, laborales.getEmpresa())
                .setParameter(3, laborales.getPuesto())
                .setParameter(4, laborales.getFechaInicio())
                .setParameter(5, laborales.getFechaFin());
        storedProcedureQueryExperienciasLaborales.execute();
        Map<String, String> resp = new HashMap<>();
        resp.put("id", String.valueOf(id));
        resp.put("message", "CREATED");

        return resp;
    }
}
