package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.*;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.model.response.TalentResp;
import com.fractal.bancodetalentos.service.TalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/bdt/talent")
@RequiredArgsConstructor
public class TalentoController {

    private final TalentoService talentoService;

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PostMapping("/new-talent")
    public ResponseEntity<GeneralResp> addNewTalent(@Valid @RequestBody NewTalentReq newTalentRequest) {
        GeneralResp newTalentResp = talentoService.addNewTalent(newTalentRequest);
        if (newTalentResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(newTalentResp, HttpStatus.CREATED);
    }

    @PostMapping("/get-talent")
    public ResponseEntity<TalentResp> getTalent(@Valid @RequestBody GetTalentReq getTalentReq) {
        TalentResp talentResp = talentoService.getTalent(getTalentReq.getIdTalent(), getTalentReq);
        if (talentResp == null) {
            throw new ResourceNotFoundException("Talent", "id", getTalentReq.getIdTalent());
        }
        return new ResponseEntity<>(talentResp, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PutMapping("/update/description")
    public ResponseEntity<GeneralResp> putDescription(@Valid @RequestBody UpdateDescriptionReq updateDescriptionReq) {
        GeneralResp generalResp = talentoService.putDescription(updateDescriptionReq.getId(), updateDescriptionReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PutMapping("/update/profile-picture")
    public ResponseEntity<GeneralResp> putProfilePicture(@Valid @RequestBody UpdateProfilePictureReq updateProfilePictureReq) {
        GeneralResp generalResp = talentoService.putProfilePicture(updateProfilePictureReq.getId(), updateProfilePictureReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PutMapping("/update/salary")
    public ResponseEntity<GeneralResp> putSalary(@Valid @RequestBody UpdateSalaryReq updateSalaryReq) {
        GeneralResp generalResp = talentoService.putSalary(updateSalaryReq.getId(), updateSalaryReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PutMapping("/update/social-links")
    public ResponseEntity<GeneralResp> putSocialLinks(@Valid @RequestBody UpdateSocialLinksReq updateSocialLinksReq) {
        GeneralResp generalResp = talentoService.putSocialLinks(updateSocialLinksReq.getId(), updateSocialLinksReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('RECLUTADOR')")
    @PutMapping("/update/availability")
    public ResponseEntity<GeneralResp> putAvailability(@Valid @RequestBody UpdateDisponibilidadReq updateDisponibilidadReq) {
        GeneralResp generalResp = talentoService.putAvailability(updateDisponibilidadReq.getId(), updateDisponibilidadReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }
}
