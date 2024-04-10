package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.NewUserListReq;
import com.fractal.bancodetalentos.model.request.UserFavListReq;
import com.fractal.bancodetalentos.model.response.NewUserListResp;
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

    @PostMapping("/new")
    public ResponseEntity<NewUserListResp> addNewUserList(@Valid @RequestBody NewUserListReq newUserListReq) {
        NewUserListResp newUserListResp = listaUsuarioService.addNewUserList(newUserListReq.getId(), newUserListReq);
        if (newUserListResp == null) {
            throw new ResourceNotFoundException("Lista de Favoritos");
        }
        return new ResponseEntity<>(newUserListResp, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<List<UserListResp>> getUserLists(@Valid @RequestBody UserFavListReq userFavListReq) {
        List<UserListResp> userListResp = listaUsuarioService.getUserLists(userFavListReq.getId());
        if (userListResp == null) {
            throw new ResourceNotFoundException("Lista de Favoritos");
        }
        return new ResponseEntity<>(userListResp, HttpStatus.OK);
    }

}
