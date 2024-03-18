package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.NewFeedbackReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.model.response.TalentResp;
import com.fractal.bancodetalentos.service.FeedbackTalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/bdt/feedback")
@RequiredArgsConstructor
public class FeedbackTalentoController {

    private final FeedbackTalentoService feedbackTalentoService;

    @PostMapping("/add/{id}")
    public ResponseEntity<GeneralResp> addTalentFeedback(@Valid @RequestBody NewFeedbackReq newFeedbackReq, @PathVariable Integer id) {
        GeneralResp generalResp = feedbackTalentoService.addTalentFeedback(newFeedbackReq, id);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

}
