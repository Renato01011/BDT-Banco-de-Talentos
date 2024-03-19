package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.DuplicatedDataException;
import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.entity.BtTxListaUsuarioTalento;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.ListaUsuarioTalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service
@RequiredArgsConstructor
public class ListaUsuarioTalentoServiceImpl implements ListaUsuarioTalentoService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public GeneralResp addNewTalentToList(Integer listId, Integer talentId) {
        StoredProcedureQuery storedProcedureQueryCheckTalent = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, talentId);
        storedProcedureQueryCheckTalent.execute();
        Integer talentExists = (Integer) storedProcedureQueryCheckTalent.getOutputParameterValue(2);

        if (talentExists == 0) {
            throw new ResourceNotFoundException("Talent", "id", talentId);
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
                .setParameter(2, talentId);
        storedProcedureQueryCheckListUserTalentId.execute();
        Integer listUserTalentExists = (Integer) storedProcedureQueryCheckListUserTalentId.getOutputParameterValue(3);

        if (listUserTalentExists != 0) {
            throw new DuplicatedDataException(talentId.toString(), listId.toString());
        }

        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_ADD_TALENT_TO_LIST")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, listId)
                .setParameter(2, talentId);
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
