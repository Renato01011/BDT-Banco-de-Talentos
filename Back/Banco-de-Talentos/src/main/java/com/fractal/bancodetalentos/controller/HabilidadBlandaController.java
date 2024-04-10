package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.AddSoftSkillReq;
import com.fractal.bancodetalentos.service.HabilidadBlandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bdt/soft")
@RequiredArgsConstructor
public class HabilidadBlandaController {

    private final HabilidadBlandaService blandaService;

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addNewSoftSkill(@Valid @RequestBody AddSoftSkillReq blandas) {
        Map<String, String> resp = blandaService.addNewSoftSkill(blandas, blandas.getId());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }
}
