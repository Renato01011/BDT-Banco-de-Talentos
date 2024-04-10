package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.UpdateTalentInUserListReq;
import com.fractal.bancodetalentos.model.request.AddTalentToUserListReq;
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

    @PostMapping("/add")
    public ResponseEntity<GeneralResp> addNewTalentToList(@Valid @RequestBody AddTalentToUserListReq addTalentToUserListReq) {
        GeneralResp generalResp = listaUsuarioTalentoService.addNewTalentToList(addTalentToUserListReq.getListId(), addTalentToUserListReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<GeneralResp> editTalentUserList(@Valid @RequestBody UpdateTalentInUserListReq updateTalentInUserListReq) {
        GeneralResp generalResp = listaUsuarioTalentoService.editTalentUserList(updateTalentInUserListReq.getListUserTalentId(), updateTalentInUserListReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.CREATED);
    }

}
