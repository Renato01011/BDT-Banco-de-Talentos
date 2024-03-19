package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.entity.BtTxListaUsuarioTalento;
import com.fractal.bancodetalentos.model.response.GeneralResp;

public interface ListaUsuarioTalentoService {

    GeneralResp addNewTalentToList(Integer listId, Integer talentId);
    void delete (BtTxListaUsuarioTalento listaUsuarioTalento);
}
