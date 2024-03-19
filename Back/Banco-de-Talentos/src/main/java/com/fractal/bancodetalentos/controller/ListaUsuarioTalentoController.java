package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.NewUserListReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.ListaUsuarioTalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/bdt/list-user")
@RequiredArgsConstructor
public class ListaUsuarioTalentoController {

    private final ListaUsuarioTalentoService listaUsuarioTalentoService;

    @PostMapping("/add/{listId}/{talentId}")
    public ResponseEntity<GeneralResp> addNewTalentToList(@PathVariable Integer listId, @PathVariable Integer talentId) {
        GeneralResp generalResp = listaUsuarioTalentoService.addNewTalentToList(listId, talentId);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.CREATED);
    }

}
