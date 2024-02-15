package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.NewTalentReq;
import com.fractal.bancodetalentos.model.response.PostResp;
import com.fractal.bancodetalentos.service.TalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Talento/v1")
@RequiredArgsConstructor
public class TalentoController {

    private final TalentoService talentoService;

    @PostMapping("/new-talent")
    public PostResp addNewTalent(@RequestBody NewTalentReq newTalentRequest) { return talentoService.addNewTalent(newTalentRequest); }

}
