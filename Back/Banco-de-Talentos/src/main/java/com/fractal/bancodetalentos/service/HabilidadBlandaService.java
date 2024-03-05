package com.fractal.bancodetalentos.service;


import com.fractal.bancodetalentos.model.request.HabilidadesBlandas;

import java.util.Map;

public interface HabilidadBlandaService {

    Map<String, String> addNewSoftSkill(HabilidadesBlandas blanda, Integer id);
}
