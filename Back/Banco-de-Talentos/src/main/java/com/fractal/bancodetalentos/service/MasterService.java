package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.response.*;

import java.math.BigDecimal;
import java.util.List;

public interface MasterService {

    List<LanguageResp> getLanguage();

    List<RolResp> getRol();

    List<CurrenciesResp> getCurrencies();

    List<ProfileResp> getProfile();

    List<LangProficiencyResp> getLangProficiency();

    List<CountryResp> getCountry();

    List<CityResp> getCityById(BigDecimal countryId);
}
