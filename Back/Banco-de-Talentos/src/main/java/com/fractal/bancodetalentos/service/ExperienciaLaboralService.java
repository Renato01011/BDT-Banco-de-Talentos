package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.ExperienciasLaborales;

import java.util.Map;

public interface ExperienciaLaboralService {

    Map<String, String> addNewWorkExp(ExperienciasLaborales laborales, Integer id);
}
