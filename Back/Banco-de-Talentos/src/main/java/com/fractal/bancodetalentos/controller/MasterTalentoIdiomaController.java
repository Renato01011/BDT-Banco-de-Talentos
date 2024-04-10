package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.AddLanguageReq;
import com.fractal.bancodetalentos.model.request.DeleteLanguageReq;
import com.fractal.bancodetalentos.model.request.UpdateLanguageReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.MasterTalentoIdiomaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bdt/lang")
@RequiredArgsConstructor
public class MasterTalentoIdiomaController {

    private final MasterTalentoIdiomaService idiomaService;

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addNewLangExp(@Valid @RequestBody AddLanguageReq idiomas) {
        Map<String, String> resp = idiomaService.addNewLanguage(idiomas, idiomas.getId());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PutMapping("/update")
    public ResponseEntity<GeneralResp> putLangExp(@Valid @RequestBody UpdateLanguageReq idiomas) {
        GeneralResp generalResp = idiomaService.putLangExp(idiomas.getIdTalent(), idiomas.getIdTalentLang(), idiomas);
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PostMapping("/delete")
    public ResponseEntity<GeneralResp> deleteTalentLang(@Valid @RequestBody DeleteLanguageReq languageReq) {
        GeneralResp generalResp = idiomaService.deleteTalentLang(languageReq.getIdTalent(), languageReq.getIdTalentLang());
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }
}
