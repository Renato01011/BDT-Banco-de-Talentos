package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.Idiomas;

import java.util.Map;

public interface MasterTalentoIdiomaService {

    Map<String, String> addNewLanguage(Idiomas idiomas, Integer id);
}
