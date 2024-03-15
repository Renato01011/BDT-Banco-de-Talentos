package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.ExperienciasEducativas;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.ExperienciaEducativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bdt/educ")
@RequiredArgsConstructor
public class ExperienciaEducativaController {

    private final ExperienciaEducativaService educativaService;

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PostMapping("/add/{id}")
    public ResponseEntity<Map<String, String>> addNewEducExp(@PathVariable Integer id, @Valid @RequestBody ExperienciasEducativas educativas) {
        Map<String, String> resp = educativaService.addNewEducExp(educativas, id);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PutMapping("/update/{idTalent}/{idEducExp}")
    public ResponseEntity<GeneralResp> putEducExp(@PathVariable Integer idTalent, @PathVariable Integer idEducExp, @Valid @RequestBody ExperienciasEducativas experienciasEducativas) {
        GeneralResp generalResp = educativaService.putEducExp(idTalent, idEducExp, experienciasEducativas);
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idTalent}/{idEducExp}")
    public ResponseEntity<GeneralResp> deleteEducExp(@PathVariable Integer idTalent, @PathVariable Integer idEducExp) {
        GeneralResp generalResp = educativaService.deleteEducExp(idTalent, idEducExp);
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }
}
