package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.NewTalentReq;
import com.fractal.bancodetalentos.model.response.PostResp;
import com.fractal.bancodetalentos.service.TalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/bdt/talent")
@RequiredArgsConstructor
public class TalentoController {

    private final TalentoService talentoService;

    @PostMapping("/new-talent")
    public PostResp addNewTalent(@Valid @RequestBody NewTalentReq newTalentRequest) { return talentoService.addNewTalent(newTalentRequest); }

}
