package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.AddWorkExpReq;
import com.fractal.bancodetalentos.model.request.DeleteWorkExpReq;
import com.fractal.bancodetalentos.model.request.UpdateWorkExpReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.ExperienciaLaboralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bdt/work")
@RequiredArgsConstructor
public class ExperienciaLaboralController {

    private final ExperienciaLaboralService laboralService;

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addNewWorkExp(@Valid @RequestBody AddWorkExpReq laborales) {
        Map<String, String> resp = laboralService.addNewWorkExp(laborales, laborales.getId());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PutMapping("/update")
    public ResponseEntity<GeneralResp> putWorkExp(@Valid @RequestBody UpdateWorkExpReq workExpReq) {
        GeneralResp generalResp = laboralService.putWorkExp(workExpReq.getIdTalent(), workExpReq.getIdWorkExp(), workExpReq);
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PostMapping("/delete")
    public ResponseEntity<GeneralResp> deleteWorkExp(@Valid @RequestBody DeleteWorkExpReq workExpReq) {
        GeneralResp generalResp = laboralService.deleteWorkExp(workExpReq.getIdTalent(), workExpReq.getIdWorkExp());
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }
}
