package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.dto.TmUsuarioDTO;
import com.fractal.bancodetalentos.model.entity.BtTxMasterUsuario;

import java.util.Optional;

public interface MasterUsuarioService {

    /*public Optional<TmUsuarioDTO> findByUsername(String username);*/

    public TmUsuarioDTO findByUsername(String username);

    public Boolean existsByUsername(String username);
}
