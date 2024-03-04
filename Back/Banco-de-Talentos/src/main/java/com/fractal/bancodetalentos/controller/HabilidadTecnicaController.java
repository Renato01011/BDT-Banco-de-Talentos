package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.HabilidadesTecnicas;
import com.fractal.bancodetalentos.model.response.TecSkillsResp;
import com.fractal.bancodetalentos.service.HabilidadTecnicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/add/{id}")
    public ResponseEntity<Map<String, String>> addNewTechSkill(@PathVariable Integer id, @Valid @RequestBody HabilidadesTecnicas tecnicas) {
        Map<String, String> resp = tecnicaService.addNewTechSkill(tecnicas, id);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

}
