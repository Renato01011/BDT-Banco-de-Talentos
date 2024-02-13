package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.model.entity.BtTmMaster;
import com.fractal.bancodetalentos.model.response.*;
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

    @Override
    public List<RolResp> getRol() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("GET_ROLE").registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.execute();
        List<Object[]> list = storedProcedureQuery.getResultList();
        List<RolResp> rolRespList = new ArrayList<>();
        for (Object[] objects: list) {
            RolResp rolResp = new RolResp();
            rolResp.setId((BigDecimal) objects[0]);
            rolResp.setName((String) objects[1]);
            rolResp.setCode((String) objects[2]);
            rolRespList.add(rolResp);
        }
        return rolRespList;
    }

    @Override
    public List<CurrenciesResp> getCurrencies() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("GET_CURRENCIES").registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.execute();
        List<Object[]> list = storedProcedureQuery.getResultList();
        List<CurrenciesResp> currenciesResps = new ArrayList<>();
        for (Object[] objects: list) {
            CurrenciesResp currResp = new CurrenciesResp();
            currResp.setId((BigDecimal) objects[0]);
            currResp.setName((String) objects[1]);
            currResp.setCode((String) objects[2]);
            currenciesResps.add(currResp);
        }
        return currenciesResps;
    }

    @Override
    public List<ProfileResp> getProfile() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("GET_PROFILE").registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.execute();
        List<Object[]> list = storedProcedureQuery.getResultList();
        List<ProfileResp> profileResps = new ArrayList<>();
        for (Object[] objects: list) {
            ProfileResp profile = new ProfileResp();
            profile.setId((BigDecimal) objects[0]);
            profile.setName((String) objects[1]);
            profile.setCode((String) objects[2]);
            profileResps.add(profile);
        }
        return profileResps;
    }

    @Override
    public List<LangProficiencyResp> getLangProficiency() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("GET_LANG_PROFICIENCY").registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.execute();
        List<Object[]> list = storedProcedureQuery.getResultList();
        List<LangProficiencyResp> proficiencyResp = new ArrayList<>();
        for (Object[] objects: list) {
            LangProficiencyResp resp = new LangProficiencyResp();
            resp.setId((BigDecimal) objects[0]);
            resp.setName((String) objects[1]);
            proficiencyResp.add(resp);
        }
        return proficiencyResp;
    }


}
