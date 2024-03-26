package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.DuplicatedDataException;
import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.HabilidadesTecnicas;
import com.fractal.bancodetalentos.model.response.TecSkillsResp;
import com.fractal.bancodetalentos.service.HabilidadTecnicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class HabilidadTecnicaServiceImpl implements HabilidadTecnicaService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<TecSkillsResp> getTecSkillsResp() {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SP_GET_TEC_SKILL");
        storedProcedure.execute();
        List<String> list = storedProcedure.getResultList();
        List<TecSkillsResp> result = new ArrayList<>();
        for (String skill : list) {
            result.add(new TecSkillsResp(skill));
        }
        return result;
    }

    @Override
    public Map<String, String> addNewTechSkill(HabilidadesTecnicas tecnicas, Integer id) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }

        StoredProcedureQuery storedProcedureQueryCheckSkill = entityManager.createStoredProcedureQuery("SP_CHECK_TECH_SKILL")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT)
                .setParameter(1, id)
                .setParameter(2, tecnicas.getNombre());
        storedProcedureQueryCheckSkill.execute();
        Integer existsSkill = (Integer) storedProcedureQueryCheckSkill.getOutputParameterValue(3);

        if (existsSkill == 1) {
            throw new DuplicatedDataException(tecnicas.getNombre());
        }
        
        StoredProcedureQuery storedProcedureQueryHabilidadTecnica = entityManager
                .createStoredProcedureQuery("SP_ADD_TECHNICAL_ABILITY")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, BigDecimal.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, tecnicas.getNombre())
                .setParameter(3, tecnicas.getAnios());
        storedProcedureQueryHabilidadTecnica.execute();

        Map<String, String> resp = new HashMap<>();
        resp.put("id", String.valueOf(id));
        resp.put("message", "CREATED");

        return resp;
    }

}
