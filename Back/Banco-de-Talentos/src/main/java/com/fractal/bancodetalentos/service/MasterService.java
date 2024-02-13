package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.entity.BtTmMaster;
import com.fractal.bancodetalentos.model.response.LanguageResp;

import java.util.List;

public interface MasterService {

    List<BtTmMaster> searchLanguage();

    List<LanguageResp> getLanguage();
}
