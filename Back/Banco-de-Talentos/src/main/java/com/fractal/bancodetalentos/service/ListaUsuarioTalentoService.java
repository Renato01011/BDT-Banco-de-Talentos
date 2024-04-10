package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.UpdateTalentInUserListReq;
import com.fractal.bancodetalentos.model.request.AddTalentToUserListReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;

public interface ListaUsuarioTalentoService {

    GeneralResp addNewTalentToList(Integer listId, AddTalentToUserListReq addTalentToUserListReq);
    GeneralResp editTalentUserList(Integer listUserTalentId, UpdateTalentInUserListReq updateTalentInUserListReq);
}
