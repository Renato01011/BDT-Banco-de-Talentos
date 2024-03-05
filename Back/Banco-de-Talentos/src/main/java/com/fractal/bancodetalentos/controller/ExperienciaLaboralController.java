package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.ExperienciasLaborales;
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
}
