package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.response.*;

import java.util.List;

public interface MasterService {

    List<LanguageResp> getLanguage();

    List<RoleResp> getRol();

    List<CurrenciesResp> getCurrencies();

    List<ProfileResp> getProfile();

    List<LangProficiencyResp> getLangProficiency();

    List<CountryResp> getCountry();

    List<CityResp> getCityById(Integer countryId);
}
