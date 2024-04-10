package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.AddEducExpReq;
import com.fractal.bancodetalentos.model.request.DeleteEducExpReq;
import com.fractal.bancodetalentos.model.request.UpdateEducExpReq;
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
    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addNewEducExp(@Valid @RequestBody AddEducExpReq educativas) {
        Map<String, String> resp = educativaService.addNewEducExp(educativas, educativas.getId());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PutMapping("/update")
    public ResponseEntity<GeneralResp> putEducExp(@Valid @RequestBody UpdateEducExpReq educExpReq) {
        GeneralResp generalResp = educativaService.putEducExp(educExpReq.getIdTalent(), educExpReq.getIdEducExp(), educExpReq);
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PostMapping("/delete")
    public ResponseEntity<GeneralResp> deleteEducExp(@Valid @RequestBody DeleteEducExpReq educExpReq) {
        GeneralResp generalResp = educativaService.deleteEducExp(educExpReq.getIdTalent(), educExpReq.getIdEducExp());
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }
}
