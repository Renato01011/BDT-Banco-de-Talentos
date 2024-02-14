package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.entity.BtTmMaster;
import com.fractal.bancodetalentos.model.response.*;
import com.fractal.bancodetalentos.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MasterController {

    private final MasterService masterService;

    @GetMapping("/language")
    public List<LanguageResp> find() { return masterService.getLanguage(); }

    @GetMapping("/role")
    public List<RolResp> findRol() { return masterService.getRol(); }

    @GetMapping("/currencies")
    public List<CurrenciesResp> findCurrencies() { return masterService.getCurrencies(); }

    @GetMapping("/profile")
    public List<ProfileResp> findProfile() { return masterService.getProfile(); }

    @GetMapping("/proficiency")
    public List<LangProficiencyResp> findLangProficiency() { return masterService.getLangProficiency(); }

    @GetMapping("/countries")
    public List<CountryResp> findCountry() { return masterService.getCountry(); }

    @GetMapping("/cities/{countryId}")
    public List<CityResp> findCityById(@PathVariable BigDecimal countryId) { return masterService.getCityById(countryId); }
}
