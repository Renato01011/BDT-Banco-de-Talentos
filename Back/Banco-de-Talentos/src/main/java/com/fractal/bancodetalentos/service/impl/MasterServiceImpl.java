package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.model.entity.BtTmMaster;
import com.fractal.bancodetalentos.model.response.LanguageResp;
import com.fractal.bancodetalentos.repository.BtTmMasterRepositorio;
import com.fractal.bancodetalentos.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {

    private final BtTmMasterRepositorio masterRepositorio;

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<BtTmMaster> searchLanguage() {
        return masterRepositorio.searchLanguage();
    }

    @Override
    public List<LanguageResp> getLanguage() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("GET_LANGUAGE").registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.execute();
        List<Object[]> result = storedProcedureQuery.getResultList();
        List<LanguageResp> respList = new ArrayList<>();
        for (Object[] objects: result) {
            LanguageResp languageResp = new LanguageResp();
            languageResp.setId((BigDecimal) objects[0]);
            languageResp.setName((String) objects[1]);
            languageResp.setCode((String) objects[2]);
            respList.add(languageResp);
        }
        return respList;
    }
}
