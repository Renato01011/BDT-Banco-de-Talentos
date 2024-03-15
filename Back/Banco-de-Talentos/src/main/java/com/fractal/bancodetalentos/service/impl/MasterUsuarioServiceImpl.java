package com.fractal.bancodetalentos.service.impl;


import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.dto.TmUsuarioDTO;
import com.fractal.bancodetalentos.model.dto.TxRolDTO;

import com.fractal.bancodetalentos.service.MasterUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterUsuarioServiceImpl implements MasterUsuarioService {

    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    public TmUsuarioDTO findByUsername(String username) {
        Boolean existsUser = existsByUsername(username);
        if (Boolean.FALSE.equals(existsUser)) {
            throw new ResourceNotFoundException(username);
        }

        StoredProcedureQuery storedProcedureQueryUser = entityManager
                .createStoredProcedureQuery("SP_GET_USER_BY_NAME")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .setParameter(1, username);
        storedProcedureQueryUser.execute();
        List<Object[]> users = storedProcedureQueryUser.getResultList();
        TmUsuarioDTO usuarioDTO = new TmUsuarioDTO();

        for (Object[] objects : users) {
            usuarioDTO.setIdUsuario((Integer) objects[0]);
            usuarioDTO.setNoNombre((String) objects[1]);
            usuarioDTO.setApApellidoPaterno((String) objects[2]);
            usuarioDTO.setApApellidoMaterno((String) objects[3]);
            usuarioDTO.setImImagen((byte[]) objects[4]);
            usuarioDTO.setUsUsuario((String) objects[5]);
            usuarioDTO.setPwPassword((String) objects[6]);
        }

        Integer userID = usuarioDTO.getIdUsuario();

        StoredProcedureQuery storedProcedureQueryRoles = entityManager
                .createStoredProcedureQuery("SP_GET_ROL_BY_USER_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, userID);
        storedProcedureQueryRoles.execute();
        List<Object[]> roles = storedProcedureQueryRoles.getResultList();

        List<TxRolDTO> rolDTOList = new ArrayList<>();

        for (Object[] objects : roles) {
            TxRolDTO rolDTO = new TxRolDTO();
            rolDTO.setIdUsuario((Integer) objects[0]);
            rolDTO.setIdRol((Integer) objects[1]);
            rolDTO.setName((String) objects[2]);
            rolDTOList.add(rolDTO);
        }
        usuarioDTO.setRoles(rolDTOList);

        return usuarioDTO;
    }

    @Override
    public Boolean existsByUsername(String username) {
        StoredProcedureQuery storedProcedureQueryCheck = entityManager.createStoredProcedureQuery("SP_EXISTS_BY_NAME")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, username);
        storedProcedureQueryCheck.execute();
        Integer existsUser = (Integer) storedProcedureQueryCheck.getOutputParameterValue(2);
        return existsUser == 1;
    }
}
