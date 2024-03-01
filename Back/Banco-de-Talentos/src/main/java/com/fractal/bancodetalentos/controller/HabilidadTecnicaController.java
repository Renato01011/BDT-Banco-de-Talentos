package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.response.TecSkillsResp;
import com.fractal.bancodetalentos.service.HabilidadTecnicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bdt/tech")
@RequiredArgsConstructor
public class HabilidadTecnicaController {

    private final HabilidadTecnicaService tecnicaService;

    @GetMapping("/skills")
    public ResponseEntity<List<TecSkillsResp>> findTecSkills() {
        List<TecSkillsResp> skills = tecnicaService.getTecSkillsResp();
        if (skills == null || skills.isEmpty()) {
            throw new ResourceNotFoundException("Technical Skills");
        }
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

}
