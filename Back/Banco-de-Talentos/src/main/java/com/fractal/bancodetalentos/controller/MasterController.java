package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.FindCityByCountryIdReq;
import com.fractal.bancodetalentos.model.response.*;
import com.fractal.bancodetalentos.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bdt/master")
@RequiredArgsConstructor
public class MasterController {

    private final MasterService masterService;

    @PostMapping("/languages")
    public ResponseEntity<List<LanguageResp>> find() {
        List<LanguageResp> language = masterService.getLanguage();
        if (language == null || language.isEmpty()) {
            throw new ResourceNotFoundException("Languages");
        }
        return new ResponseEntity<>(language, HttpStatus.OK);
    }

    @PostMapping("/roles")
    public ResponseEntity<List<RoleResp>> findRol() {
        List<RoleResp> role = masterService.getRol();
        if (role == null || role.isEmpty()) {
            throw new ResourceNotFoundException("Roles");
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/currencies")
    public ResponseEntity<List<CurrenciesResp>> findCurrencies() {
        List<CurrenciesResp> currencies = masterService.getCurrencies();
        if (currencies == null || currencies.isEmpty()) {
            throw new ResourceNotFoundException("Currencies");
        }
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

    @PostMapping("/profiles")
    public ResponseEntity<List<ProfileResp>> findProfile() {
        List<ProfileResp> profile = masterService.getProfile();
        if (profile == null || profile.isEmpty()) {
            throw new ResourceNotFoundException("Profiles");
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PostMapping("/proficiency")
    public ResponseEntity<List<LangProficiencyResp>> findLangProficiency() {
        List<LangProficiencyResp> langProficiency = masterService.getLangProficiency();
        if (langProficiency == null || langProficiency.isEmpty()) {
            throw new ResourceNotFoundException("Language Proficiency");
        }
        return new ResponseEntity<>(langProficiency, HttpStatus.OK);
    }

    @PostMapping("/countries")
    public ResponseEntity<List<CountryResp>> findCountry() {
        List<CountryResp> countryList = masterService.getCountry();
        if (countryList == null || countryList.isEmpty()) {
            throw new ResourceNotFoundException("Countries");
        }
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

    @PostMapping("/countries/cities")
    public ResponseEntity<List<CityResp>> findCityById(@Valid @RequestBody FindCityByCountryIdReq countryId) {
        List<CityResp> cityList = masterService.getCityById(countryId.getIdCountry());
        if (cityList == null || cityList.isEmpty()) {
            throw new ResourceNotFoundException("City", "id", countryId.getIdCountry());
        }
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }
}
