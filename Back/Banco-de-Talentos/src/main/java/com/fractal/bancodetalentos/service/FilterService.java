package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.FilterTalentReq;
import com.fractal.bancodetalentos.model.response.FilterTalentoResp;

import java.util.List;

public interface FilterService {
    List<FilterTalentoResp> filterTalents(FilterTalentReq filterTalentReq);
}
