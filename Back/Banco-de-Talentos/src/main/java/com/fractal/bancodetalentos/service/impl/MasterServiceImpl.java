package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.config.DataSourceConfig;
import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.response.*;
import com.fractal.bancodetalentos.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {


    @PersistenceContext
    private final EntityManager entityManager;

    private final DataSourceConfig dataSource;

    /*@Override
    public List<LanguageResp> getLanguage() {
        Query query = entityManager.createNativeQuery("CALL SP_GET_LANGUAGES");
        List<Object[]> result = query.getResultList();
        List<LanguageResp> respList = new ArrayList<>();
        for (Object[] objects : result) {
            LanguageResp languageResp = new LanguageResp();
            languageResp.setId((Integer) objects[0]);
            languageResp.setName((String) objects[1]);
            languageResp.setCode((String) objects[2]);
            respList.add(languageResp);
        }
        return respList;
    }*/

    /*@Override
    public List<RoleResp> getRol() {
        Query query = entityManager.createNativeQuery("CALL SP_GET_ROLES");
        List<Object[]> list = query.getResultList();
        List<RoleResp> roleRespList = new ArrayList<>();
        for (Object[] objects: list) {
            RoleResp roleResp = new RoleResp();
            roleResp.setId((Integer) objects[0]);
            roleResp.setName((String) objects[1]);
            roleResp.setCode((String) objects[2]);
            roleRespList.add(roleResp);
        }
        return roleRespList;
    }*/

   /* @Override
    public List<CurrenciesResp> getCurrencies() {
        Query query = entityManager.createNativeQuery("CALL SP_GET_CURRENCIES");
        List<Object[]> list = query.getResultList();
        List<CurrenciesResp> currenciesResps = new ArrayList<>();
        for (Object[] objects : list) {
            CurrenciesResp currResp = new CurrenciesResp();
            currResp.setId((Integer) objects[0]);
            currResp.setName((String) objects[1]);
            currResp.setCode((String) objects[2]);
            currenciesResps.add(currResp);
        }
        return currenciesResps;
    }*/

    /*@Override
    public List<ProfileResp> getProfile() {
        Query query = entityManager.createNativeQuery("CALL SP_GET_PROFILES");
        List<Object[]> list = query.getResultList();
        List<ProfileResp> profileResps = new ArrayList<>();
        for (Object[] objects: list) {
            ProfileResp profile = new ProfileResp();
            profile.setId((Integer) objects[0]);
            profile.setName((String) objects[1]);
            profile.setCode((String) objects[2]);
            profileResps.add(profile);
        }
        return profileResps;
    }*/

    /*@Override
    public List<LangProficiencyResp> getLangProficiency() {
        Query query = entityManager.createNativeQuery("CALL SP_GET_LANG_PROFICIENCY");
        List<Object[]> list = query.getResultList();
        List<LangProficiencyResp> proficiencyResp = new ArrayList<>();
        for (Object[] objects : list) {
            LangProficiencyResp resp = new LangProficiencyResp();
            resp.setId((Integer) objects[0]);
            resp.setName((String) objects[1]);
            proficiencyResp.add(resp);
        }
        return proficiencyResp;
    }*/

    /*@Override
    public List<CountryResp> getCountry() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_GET_COUNTRIES");
        storedProcedureQuery.execute();
        List<Object[]> result = storedProcedureQuery.getResultList();
        List<CountryResp> respList = new ArrayList<>();
        for (Object[] objects : result) {
            CountryResp countryResp = new CountryResp();
            countryResp.setId((Integer) objects[0]);
            countryResp.setCountry((String) objects[1]);
            countryResp.setCode((String) objects[2]);
            countryResp.setCallingCode((String) objects[3]);
            respList.add(countryResp);
        }
        return respList;
    }*/

    @Override
    public List<CityResp> getCityById(Integer countryId) {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_GET_CITIES")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, countryId);
        storedProcedureQuery.execute();
        List<Object[]> result = storedProcedureQuery.getResultList();
        List<CityResp> respList = new ArrayList<>();
        for (Object[] objects : result) {
            CityResp cityResp = new CityResp();
            cityResp.setId((Integer) objects[0]);
            cityResp.setCity((String) objects[1]);
            respList.add(cityResp);
        }
        return respList;
    }

    @Override
    public GeneralDataResp getGeneralData() {

        List<List<Object[]>> allResults = multiDataFromDb("CALL SP_GET_GENERAL_DATA");

        GeneralDataResp dataResp = new GeneralDataResp();
        dataResp.setLanguages(languages((allResults.get(0))));
        dataResp.setCurrencies(currencies((allResults.get(1))));
        dataResp.setProficiency(proficiency((allResults.get(2))));
        dataResp.setCountries(countries((allResults.get(3))));
        dataResp.setSkills(skills(allResults.get(4)));
        return dataResp;
    }

    private List<LanguageResp> languages(List<Object[]> list) {
        if (list == null || list.isEmpty()) {
            throw new ResourceNotFoundException("Languages");
        }
        List<LanguageResp> languageResps = new ArrayList<>();
        for (Object[] objects : list) {
            LanguageResp languageResp = new LanguageResp();
            languageResp.setId((Integer) objects[0]);
            languageResp.setName((String) objects[1]);
            languageResp.setCode((String) objects[2]);
            languageResps.add(languageResp);
        }
        return languageResps;
    }

    private List<CurrenciesResp> currencies(List<Object[]> list) {
        if (list == null || list.isEmpty()) {
            throw new ResourceNotFoundException("Currencies");
        }
        List<CurrenciesResp> currenciesResps = new ArrayList<>();
        for (Object[] objects : list) {
            CurrenciesResp currResp = new CurrenciesResp();
            currResp.setId((Integer) objects[0]);
            currResp.setName((String) objects[1]);
            currResp.setCode((String) objects[2]);
            currenciesResps.add(currResp);
        }
        return currenciesResps;
    }

    private List<LangProficiencyResp> proficiency(List<Object[]> list) {
        if (list == null || list.isEmpty()) {
            throw new ResourceNotFoundException("Language Proficiency");
        }
        List<LangProficiencyResp> proficiencyResp = new ArrayList<>();
        for (Object[] objects : list) {
            LangProficiencyResp resp = new LangProficiencyResp();
            resp.setId((Integer) objects[0]);
            resp.setName((String) objects[1]);
            proficiencyResp.add(resp);
        }
        return proficiencyResp;
    }

    private List<CountryResp> countries(List<Object[]> list) {
        if (list == null || list.isEmpty()) {
            throw new ResourceNotFoundException("Countries");
        }
        List<CountryResp> respList = new ArrayList<>();
        for (Object[] objects : list) {
            CountryResp countryResp = new CountryResp();
            countryResp.setId((Integer) objects[0]);
            countryResp.setCountry((String) objects[1]);
            countryResp.setCode((String) objects[2]);
            countryResp.setCallingCode((String) objects[3]);
            respList.add(countryResp);
        }
        return respList;
    }

    private List<TecSkillsResp> skills(List<Object[]> list) {
        if (list == null || list.isEmpty()) {
            throw new ResourceNotFoundException("Skills");
        }
        List<TecSkillsResp> result = new ArrayList<>();
        for (Object[] skill : list) {
            TecSkillsResp resp = new TecSkillsResp();
            resp.setName((String) skill[0]);
            result.add(resp);
        }
        return result;
    }

    private List<List<Object[]>> multiDataFromDb(String sp) {
        List<List<Object[]>> results = new ArrayList<>();
        try (Connection connection = dataSource.dataSource().getConnection()) {
            try (CallableStatement statement = connection.prepareCall(sp)) {
                boolean isResultSet = statement.execute();
                while (isResultSet) {
                    ResultSet resultSet = statement.getResultSet();
                    List<Object[]> singleResultSet = new ArrayList<>();
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 0; i < columnCount; i++) {
                            row[i] = resultSet.getObject(i + 1);
                        }
                        singleResultSet.add(row);
                    }
                    results.add(singleResultSet);
                    resultSet.close();
                    isResultSet = statement.getMoreResults();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

}
