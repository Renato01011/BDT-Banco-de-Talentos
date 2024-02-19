package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.response.*;
import com.fractal.bancodetalentos.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bdt/master")
@RequiredArgsConstructor
public class MasterController {

    private final MasterService masterService;

    @GetMapping("/languages")
    public ResponseEntity<?> find() {
        List<LanguageResp> language = masterService.getLanguage();
        if (language == null || language.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return new ResponseEntity<>(language, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<?> findRol() {
        List<RoleResp> role = masterService.getRol();
        if (role == null || role.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("/currencies")
    public ResponseEntity<?> findCurrencies() {
        List<CurrenciesResp> currencies = masterService.getCurrencies();
        if (currencies == null || currencies.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

    @GetMapping("/profiles")
    public ResponseEntity<?> findProfile() {
        List<ProfileResp> profile = masterService.getProfile();
        if (profile == null || profile.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return  new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("/proficiency")
    public ResponseEntity<?> findLangProficiency() {
        List<LangProficiencyResp> langProficiency = masterService.getLangProficiency();
        if (langProficiency == null || langProficiency.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return  new ResponseEntity<>(langProficiency, HttpStatus.OK);
    }

    @GetMapping("/countries")
    public List<CountryResp> findCountry() { return masterService.getCountry(); }

    @GetMapping("/cities/{countryId}")
    public List<CityResp> findCityById(@PathVariable Integer countryId) { return masterService.getCityById(countryId); }
}
