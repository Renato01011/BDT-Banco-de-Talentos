package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.model.response.TecSkillsResp;
import com.fractal.bancodetalentos.service.HabilidadTecnicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;


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

}
