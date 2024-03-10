package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.ExperienciasLaborales;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.ExperienciaLaboralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bdt/work")
@RequiredArgsConstructor
public class ExperienciaLaboralController {

    private final ExperienciaLaboralService laboralService;

    @PostMapping("/add/{id}")
    public ResponseEntity<Map<String, String>> addNewWorkExp(@PathVariable Integer id, @Valid @RequestBody ExperienciasLaborales laborales) {
        Map<String, String> resp = laboralService.addNewWorkExp(laborales, id);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PutMapping("/update/{idTalent}/{idWorkExp}")
    public ResponseEntity<GeneralResp> putWorkExp(@PathVariable Integer idTalent, @PathVariable Integer idWorkExp, @Valid @RequestBody ExperienciasLaborales experienciasLaborales) {
        GeneralResp generalResp = laboralService.putWorkExp(idTalent, idWorkExp, experienciasLaborales);
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idTalent}/{idWorkExp}")
    public ResponseEntity<GeneralResp> deleteWorkExp(@PathVariable Integer idTalent, @PathVariable Integer idWorkExp) {
        GeneralResp generalResp = laboralService.deleteWorkExp(idTalent, idWorkExp);
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }
}
