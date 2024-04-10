package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.request.FilterTalentReq;
import com.fractal.bancodetalentos.model.response.FilterTalentoResp;
import com.fractal.bancodetalentos.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bdt/filter")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;

    @PostMapping()
    //public ResponseEntity<List<FilterTalentoResp>> filterTalents(@Valid @RequestBody FilterTalentReq filterTalentReq) {
    public List<FilterTalentoResp> filterTalents(@Valid @RequestBody FilterTalentReq filterTalentReq) {
        return filterService.filterTalents(filterTalentReq);
        /*List<FilterTalentoResp> talents = filterService.filterTalents(filterTalentReq);
        if (talents == null || talents.isEmpty()) {
            throw new ResourceNotFoundException("Filter");
        }
        return new ResponseEntity<>(talents, HttpStatus.OK);*/
    }

}
