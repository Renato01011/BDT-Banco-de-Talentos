package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.DuplicatedDataException;
import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.entity.BtTxListaUsuarioTalento;
import com.fractal.bancodetalentos.model.request.EditUserTalentListReq;
import com.fractal.bancodetalentos.model.request.NewUserListTalentReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.ListaUsuarioTalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class ListaUsuarioTalentoServiceImpl implements ListaUsuarioTalentoService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public GeneralResp addNewTalentToList(Integer listId, NewUserListTalentReq newUserListTalentReq) {
        StoredProcedureQuery storedProcedureQueryCheckTalent = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, newUserListTalentReq.getTalentId());
        storedProcedureQueryCheckTalent.execute();
        Integer talentExists = (Integer) storedProcedureQueryCheckTalent.getOutputParameterValue(2);

        if (talentExists == 0) {
            throw new ResourceNotFoundException("Talent", "id", newUserListTalentReq.getTalentId());
        }

        StoredProcedureQuery storedProcedureQueryCheckListUserId = entityManager.createStoredProcedureQuery("SP_CHECK_LIST_USER_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, listId);
        storedProcedureQueryCheckListUserId.execute();
        Integer listUserExists = (Integer) storedProcedureQueryCheckListUserId.getOutputParameterValue(2);

        if (listUserExists == 0) {
            throw new ResourceNotFoundException("Lista Usuario", "id", listId);
        }

        StoredProcedureQuery storedProcedureQueryCheckListUserTalentId = entityManager.createStoredProcedureQuery("SP_CHECK_LIST_USER_TALENT_EXISTS")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT)
                .setParameter(1, listId)
                .setParameter(2, newUserListTalentReq.getTalentId());
        storedProcedureQueryCheckListUserTalentId.execute();
        Integer listUserTalentExists = (Integer) storedProcedureQueryCheckListUserTalentId.getOutputParameterValue(3);

        if (listUserTalentExists != 0) {
            throw new DuplicatedDataException(newUserListTalentReq.getTalentId().toString(), listId.toString());
        }

        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_ADD_TALENT_TO_LIST")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, listId)
                .setParameter(2, newUserListTalentReq.getTalentId());
        storedProcedureQuery.execute();

        GeneralResp temp = new GeneralResp();
        temp.setCode(200);
        temp.setMessage("Correctly Added");
        return temp;
    }

    @Override
    public GeneralResp editTalentUserList(Integer listUserTalentId, EditUserTalentListReq editUserTalentListReq) {
        StoredProcedureQuery storedProcedureQueryCheckListUserId = entityManager.createStoredProcedureQuery("SP_CHECK_LIST_USER_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, editUserTalentListReq.getNewUserListId());
        storedProcedureQueryCheckListUserId.execute();
        Integer listUserExists = (Integer) storedProcedureQueryCheckListUserId.getOutputParameterValue(2);

        if (listUserExists == 0) {
            throw new ResourceNotFoundException("Lista Usuario", "id", editUserTalentListReq.getNewUserListId());
        }

        StoredProcedureQuery storedProcedureQueryCheckListUserTalentId = entityManager.createStoredProcedureQuery("SP_CHECK_USER_TALENT_LIST_EXISTS_BY_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, listUserTalentId);
        storedProcedureQueryCheckListUserTalentId.execute();
        Integer listUserTalentExists = (Integer) storedProcedureQueryCheckListUserTalentId.getOutputParameterValue(2);

        if (listUserTalentExists == 0) {
            throw new ResourceNotFoundException("Lista Usuario Talento", "id", listUserTalentId);
        }

        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_EDIT_TALENT_USER_LIST")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, listUserTalentId)
                .setParameter(2, editUserTalentListReq.getNewUserListId());
        storedProcedureQuery.execute();

        GeneralResp temp = new GeneralResp();
        temp.setCode(200);
        temp.setMessage("Correctly Added");
        return temp;
    }

    @Override
    public void delete(BtTxListaUsuarioTalento listaUsuarioTalento) {

    }
}
