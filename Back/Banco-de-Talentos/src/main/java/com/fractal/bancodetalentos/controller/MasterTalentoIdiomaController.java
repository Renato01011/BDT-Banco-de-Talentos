package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.Idiomas;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.MasterTalentoIdiomaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bdt/lang")
@RequiredArgsConstructor
public class MasterTalentoIdiomaController {

    private final MasterTalentoIdiomaService idiomaService;

    @PostMapping("/add/{id}")
    public ResponseEntity<Map<String, String>> addNewLangExp(@PathVariable Integer id, @Valid @RequestBody Idiomas idiomas) {
        Map<String, String> resp = idiomaService.addNewLanguage(idiomas, id);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PutMapping("/update/{idTalent}/{idTalentLang}")
    public ResponseEntity<GeneralResp> putLangExp(@PathVariable Integer idTalent, @PathVariable Integer idTalentLang, @Valid @RequestBody Idiomas idiomas) {
        GeneralResp generalResp = idiomaService.putLangExp(idTalent, idTalentLang, idiomas);
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }
}
