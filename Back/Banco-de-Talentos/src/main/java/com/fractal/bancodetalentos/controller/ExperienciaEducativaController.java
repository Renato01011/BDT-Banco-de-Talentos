package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.ExperienciasEducativas;
import com.fractal.bancodetalentos.service.ExperienciaEducativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bdt/educ")
@RequiredArgsConstructor
public class ExperienciaEducativaController {

    private final ExperienciaEducativaService educativaService;

    @PostMapping("/add/{id}")
    public ResponseEntity<Map<String, String>> addNewEducExp(@PathVariable Integer id, @Valid @RequestBody ExperienciasEducativas educativas) {
        Map<String, String> resp = educativaService.addNewEducExp(educativas, id);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }
}
