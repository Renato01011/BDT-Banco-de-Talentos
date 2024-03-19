package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.NewUserListReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.model.response.UserListResp;
import com.fractal.bancodetalentos.service.ListaUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bdt/list")
@RequiredArgsConstructor
public class ListaUsuarioController {

    private final ListaUsuarioService listaUsuarioService;

    @PostMapping("/new/{id}")
    public ResponseEntity<GeneralResp> addNewUserList(@PathVariable Integer id, @Valid @RequestBody NewUserListReq newUserListReq) {
        GeneralResp generalResp = listaUsuarioService.addNewUserList(id, newUserListReq);
        if (generalResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(generalResp, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<UserListResp>> getUserLists(@PathVariable Integer id) {
        List<UserListResp> userListResp = listaUsuarioService.getUserLists(id);
        if (userListResp == null) {
            throw new ResourceNotFoundException("Talent");
        }
        return new ResponseEntity<>(userListResp, HttpStatus.OK);
    }

}
