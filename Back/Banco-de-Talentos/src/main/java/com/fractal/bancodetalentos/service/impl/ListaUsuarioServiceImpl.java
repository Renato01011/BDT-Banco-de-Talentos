package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.entity.BtTmListaUsuario;
import com.fractal.bancodetalentos.model.request.NewUserListReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.model.response.UserListResp;
import com.fractal.bancodetalentos.service.ListaUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListaUsuarioServiceImpl implements ListaUsuarioService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public GeneralResp addNewUserList(Integer id, NewUserListReq newUserListReq) {
        StoredProcedureQuery storedProcedureQueryCheckUser = entityManager.createStoredProcedureQuery("SP_CHECK_USER_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQueryCheckUser.execute();
        Integer exists = (Integer) storedProcedureQueryCheckUser.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("User", "id", id);
        }

        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_ADD_USER_LIST")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, newUserListReq.getListName());
        storedProcedureQuery.execute();

        GeneralResp temp = new GeneralResp();
        temp.setCode(200);
        temp.setMessage("Correctly Added");
        return temp;
    }

    @Override
    public List<UserListResp> getUserLists(Integer id) {
        StoredProcedureQuery storedProcedureQueryCheckUser = entityManager.createStoredProcedureQuery("SP_CHECK_USER_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQueryCheckUser.execute();
        Integer exists = (Integer) storedProcedureQueryCheckUser.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("User", "id", id);
        }

        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_GET_USER_LISTS")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, id);
        storedProcedureQuery.execute();
        List<Object[]> userListsResult = storedProcedureQuery.getResultList();
        List<UserListResp> userLists = new ArrayList<>();
        for (Object[] objects: userListsResult) {
            UserListResp userlist = new UserListResp();
            userlist.setIdListUser((Integer) objects[0]);
            userlist.setListName((String) objects[1]);
            userlist.setCreated((Date) objects[2]);
            userLists.add(userlist);
        }

        return userLists;
    }

    @Override
    public void delete(BtTmListaUsuario listaUsuario) {

    }
}
