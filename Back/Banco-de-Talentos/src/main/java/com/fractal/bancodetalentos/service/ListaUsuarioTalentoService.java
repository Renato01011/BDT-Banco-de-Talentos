package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.entity.BtTxListaUsuarioTalento;
import com.fractal.bancodetalentos.model.request.EditUserTalentListReq;
import com.fractal.bancodetalentos.model.request.NewUserListTalentReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;

public interface ListaUsuarioTalentoService {

    GeneralResp addNewTalentToList(Integer listId, NewUserListTalentReq newUserListTalentReq);
    GeneralResp editTalentUserList(Integer listUserTalentId, EditUserTalentListReq editUserTalentListReq);
    void delete (BtTxListaUsuarioTalento listaUsuarioTalento);
}
