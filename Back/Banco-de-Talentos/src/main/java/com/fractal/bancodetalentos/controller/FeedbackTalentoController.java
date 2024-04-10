package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.AddFeedbackReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.FeedbackTalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/bdt/feedback")
@RequiredArgsConstructor
public class FeedbackTalentoController {

    private final FeedbackTalentoService feedbackTalentoService;

    @PostMapping()
    public ResponseEntity<GeneralResp> addTalentFeedback(@Valid @RequestBody AddFeedbackReq feedback) {
        GeneralResp generalResp = feedbackTalentoService.addTalentFeedback(feedback, feedback.getId());
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent", "id", feedback.getId());
        }
        return new ResponseEntity<>(generalResp, HttpStatus.OK);
    }

}
