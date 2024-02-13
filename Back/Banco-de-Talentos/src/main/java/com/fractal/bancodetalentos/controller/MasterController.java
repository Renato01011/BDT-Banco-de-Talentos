package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.entity.BtTmMaster;
import com.fractal.bancodetalentos.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MasterController {

    private final MasterService masterService;

    @GetMapping("/master")
    public List<BtTmMaster> findLanguage(){
        return masterService.searchLanguage();
    }

    @GetMapping("/language")
    public List<BtTmMaster> find() { return masterService.getLanguage(); }
}
