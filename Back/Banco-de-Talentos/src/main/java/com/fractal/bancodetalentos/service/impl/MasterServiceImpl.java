package com.fractal.bancodetalentos.service.impl;

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
    public List<LanguageResp> getLanguage() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_GET_LANGUAGES");
        storedProcedureQuery.execute();
        List<Object[]> result = storedProcedureQuery.getResultList();
        List<LanguageResp> respList = new ArrayList<>();
        for (Object[] objects: result) {
            LanguageResp languageResp = new LanguageResp();
            languageResp.setId((Integer) objects[0]);
            languageResp.setName((String) objects[1]);
            languageResp.setCode((String) objects[2]);
            respList.add(languageResp);
        }
        return respList;
    }

    @Override
    public List<RoleResp> getRol() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_GET_ROLES");
        storedProcedureQuery.execute();
        List<Object[]> list = storedProcedureQuery.getResultList();
        List<RoleResp> roleRespList = new ArrayList<>();
        for (Object[] objects: list) {
            RoleResp roleResp = new RoleResp();
            roleResp.setId((Integer) objects[0]);
            roleResp.setName((String) objects[1]);
            roleResp.setCode((String) objects[2]);
            roleRespList.add(roleResp);
        }
        return roleRespList;
    }

    @Override
    public List<CurrenciesResp> getCurrencies() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_GET_CURRENCIES");
        storedProcedureQuery.execute();
        List<Object[]> list = storedProcedureQuery.getResultList();
        List<CurrenciesResp> currenciesResps = new ArrayList<>();
        for (Object[] objects: list) {
            CurrenciesResp currResp = new CurrenciesResp();
            currResp.setId((Integer) objects[0]);
            currResp.setName((String) objects[1]);
            currResp.setCode((String) objects[2]);
            currenciesResps.add(currResp);
        }
        return currenciesResps;
    }

    @Override
    public List<ProfileResp> getProfile() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_GET_PROFILES");
        storedProcedureQuery.execute();
        List<Object[]> list = storedProcedureQuery.getResultList();
        List<ProfileResp> profileResps = new ArrayList<>();
        for (Object[] objects: list) {
            ProfileResp profile = new ProfileResp();
            profile.setId((Integer) objects[0]);
            profile.setName((String) objects[1]);
            profile.setCode((String) objects[2]);
            profileResps.add(profile);
        }
        return profileResps;
    }

    @Override
    public List<LangProficiencyResp> getLangProficiency() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_GET_LANG_PROFICIENCY");
        storedProcedureQuery.execute();
        List<Object[]> list = storedProcedureQuery.getResultList();
        List<LangProficiencyResp> proficiencyResp = new ArrayList<>();
        for (Object[] objects: list) {
            LangProficiencyResp resp = new LangProficiencyResp();
            resp.setId((Integer) objects[0]);
            resp.setName((String) objects[1]);
            proficiencyResp.add(resp);
        }
        return proficiencyResp;
    }

    @Override
    public List<CountryResp> getCountry() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_COUNTRIES");
        storedProcedureQuery.execute();
        List<Object[]> result = storedProcedureQuery.getResultList();
        List<CountryResp> respList = new ArrayList<>();
        for (Object[] objects: result) {
            CountryResp countryResp = new CountryResp();
            countryResp.setId((Integer) objects[0]);
            countryResp.setCountry((String) objects[1]);
            countryResp.setCode((String) objects[2]);
            respList.add(countryResp);
        }
        return respList;
    }

    @Override
    public List<CityResp> getCityById(Integer countryId) {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_CITIES")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, countryId);
        storedProcedureQuery.execute();
        List<Object[]> result = storedProcedureQuery.getResultList();
        List<CityResp> respList = new ArrayList<>();
        for (Object[] objects: result) {
            CityResp cityResp = new CityResp();
            cityResp.setId((Integer) objects[0]);
            cityResp.setCity((String) objects[1]);
            respList.add(cityResp);
        }
        return respList;
    }

}
