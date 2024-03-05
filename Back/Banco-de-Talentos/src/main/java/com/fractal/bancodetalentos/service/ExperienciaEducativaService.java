package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.ExperienciasEducativas;

import java.util.Map;

public interface ExperienciaEducativaService {

    Map<String, String> addNewEducExp(ExperienciasEducativas educativas, Integer id);
}
