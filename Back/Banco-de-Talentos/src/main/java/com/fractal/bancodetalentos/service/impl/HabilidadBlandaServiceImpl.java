package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.DuplicatedDataException;
import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.entity.BtTdHabilidadBlanda;
import com.fractal.bancodetalentos.model.request.HabilidadesBlandas;
import com.fractal.bancodetalentos.service.HabilidadBlandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HabilidadBlandaServiceImpl implements HabilidadBlandaService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Map<String, String> addNewSoftSkill(HabilidadesBlandas blanda, Integer id) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }

        StoredProcedureQuery storedProcedureQueryCheckSkill = entityManager.createStoredProcedureQuery("SP_CHECK_SOFT_SKILL")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT)
                .setParameter(1, id)
                .setParameter(2, blanda.getNombre());
        storedProcedureQueryCheckSkill.execute();
        Integer existsSkill = (Integer) storedProcedureQueryCheckSkill.getOutputParameterValue(3);

        if (existsSkill == 1) {
            throw new DuplicatedDataException(blanda.getNombre());
        }

        StoredProcedureQuery storedProcedureQueryHabilidadBlanda = entityManager
                .createStoredProcedureQuery("SP_ADD_SOFT_SKILL")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, blanda.getNombre());
        storedProcedureQueryHabilidadBlanda.execute();

        Map<String, String> resp = new HashMap<>();
        resp.put("id", String.valueOf(id));
        resp.put("message", "CREATED");

        return resp;
    }
}
