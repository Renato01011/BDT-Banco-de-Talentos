package com.fractal.bancodetalentos.controller;

import com.fractal.bancodetalentos.model.dto.TmUsuarioDTO;
import com.fractal.bancodetalentos.model.request.UserReq;
import com.fractal.bancodetalentos.model.response.UserProfileResp;
import com.fractal.bancodetalentos.service.MasterUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bdt/user")
@RequiredArgsConstructor
public class MasterUsuarioController {

    private final MasterUsuarioService usuarioService;

    public ResponseEntity<TmUsuarioDTO> getUser(@RequestBody UserReq userReq) {
        TmUsuarioDTO usuarioDTO = usuarioService.findByUsername(userReq.getName());
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }


    @PostMapping("/picture")
    public ResponseEntity<UserProfileResp> getUserPicture(@RequestBody UserReq userReq) {
        TmUsuarioDTO usuarioDTO = usuarioService.findByUsername(userReq.getName());
        UserProfileResp userProfileResp = UserProfileResp.builder().img(usuarioDTO.getImImagen()).build();
        return new ResponseEntity<>(userProfileResp, HttpStatus.OK);
    }
}
