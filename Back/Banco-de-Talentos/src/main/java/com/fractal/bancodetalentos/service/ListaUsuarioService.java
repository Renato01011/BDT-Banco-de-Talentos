package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.entity.BtTmListaUsuario;
import com.fractal.bancodetalentos.model.request.NewUserListReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.model.response.NewUserListResp;
import com.fractal.bancodetalentos.model.response.UserListResp;

import java.util.List;

public interface ListaUsuarioService {
    NewUserListResp addNewUserList(Integer id, NewUserListReq newUserListReq);
    List<UserListResp> getUserLists(Integer id);
    void delete(BtTmListaUsuario listaUsuario);
}
