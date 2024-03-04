package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.HabilidadesBlandas;
import com.fractal.bancodetalentos.service.HabilidadBlandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bdt/soft")
@RequiredArgsConstructor
public class HabilidadBlandaController {

    private final HabilidadBlandaService blandaService;

    @PostMapping("/add/{id}")
    public ResponseEntity<Map<String, String>> addNewSoftSkill(@PathVariable Integer id, @Valid @RequestBody HabilidadesBlandas blandas) {
        Map<String, String> resp = blandaService.addNewSoftSkill(blandas, id);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }
}
