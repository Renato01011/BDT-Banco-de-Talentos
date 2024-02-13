package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.entity.BtTmMaster;
import com.fractal.bancodetalentos.model.response.*;

import java.util.List;

public interface MasterService {

    List<BtTmMaster> searchLanguage();

    List<LanguageResp> getLanguage();

    List<RolResp> getRol();

    List<CurrenciesResp> getCurrencies();

    List<ProfileResp> getProfile();

    List<LangProficiencyResp> getLangProficiency();
}
