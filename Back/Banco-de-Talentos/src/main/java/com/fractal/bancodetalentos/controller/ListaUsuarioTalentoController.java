package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.EditUserTalentListReq;
import com.fractal.bancodetalentos.model.request.NewUserListTalentReq;
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

    @PostMapping("/add/{listId}")
    public ResponseEntity<GeneralResp> addNewTalentToList(@PathVariable Integer listId, @Valid @RequestBody NewUserListTalentReq newUserListTalentReq) {
        GeneralResp generalResp = listaUsuarioTalentoService.addNewTalentToList(listId, newUserListTalentReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{listUserTalentId}")
    public ResponseEntity<GeneralResp> editTalentUserList(@PathVariable Integer listUserTalentId, @Valid @RequestBody EditUserTalentListReq editUserTalentListReq) {
        GeneralResp generalResp = listaUsuarioTalentoService.editTalentUserList(listUserTalentId, editUserTalentListReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.CREATED);
    }

}
