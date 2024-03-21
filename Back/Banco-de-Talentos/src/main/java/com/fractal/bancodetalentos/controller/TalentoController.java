package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.*;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.model.response.TalentResp;
import com.fractal.bancodetalentos.service.TalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/bdt/talent")
@RequiredArgsConstructor
public class TalentoController {

    private final TalentoService talentoService;

    @PostMapping("/new-talent")
    public ResponseEntity<GeneralResp> addNewTalent(@Valid @RequestBody NewTalentReq newTalentRequest) {
        GeneralResp newTalentResp = talentoService.addNewTalent(newTalentRequest);
        if (newTalentResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(newTalentResp, HttpStatus.CREATED);
    }

    @PostMapping("/get-talent/{id}")
    public ResponseEntity<TalentResp> getTalent(@PathVariable Integer id, @Valid @RequestBody GetTalentReq getTalentReq) {
        TalentResp talentResp = talentoService.getTalent(id, getTalentReq);
        if (talentResp == null) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }
        return new ResponseEntity<>(talentResp, HttpStatus.OK);
    }

    @PutMapping("/update/description/{id}")
    public ResponseEntity<GeneralResp> putDescription(@PathVariable Integer id, @Valid @RequestBody DescriptionReq descriptionReq) {
        GeneralResp generalResp = talentoService.putDescription(id, descriptionReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

    @PutMapping("/update/profile-picture/{id}")
    public ResponseEntity<GeneralResp> putProfilePicture(@PathVariable Integer id, @Valid @RequestBody ProfilePictureReq profilePictureReq) {
        GeneralResp generalResp = talentoService.putProfilePicture(id, profilePictureReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

    @PutMapping("/update/salary/{id}")
    public ResponseEntity<GeneralResp> putSalary(@PathVariable Integer id, @Valid @RequestBody SalaryReq salaryReq) {
        GeneralResp generalResp = talentoService.putSalary(id, salaryReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

    @PutMapping("/update/social-links/{id}")
    public ResponseEntity<GeneralResp> putSocialLinks(@PathVariable Integer id, @Valid @RequestBody SocialLinksReq socialLinksReq) {
        GeneralResp generalResp = talentoService.putSocialLinks(id, socialLinksReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }
}
