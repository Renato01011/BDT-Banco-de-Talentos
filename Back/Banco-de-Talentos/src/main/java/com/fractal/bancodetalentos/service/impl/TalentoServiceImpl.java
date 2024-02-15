package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.model.entity.BtTmTalento;
import com.fractal.bancodetalentos.model.request.NewTalentReq;
import com.fractal.bancodetalentos.model.response.PostResp;
import com.fractal.bancodetalentos.repository.BtTmTalentoRepositorio;
import com.fractal.bancodetalentos.service.TalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TalentoServiceImpl implements TalentoService {

    private final BtTmTalentoRepositorio talentoRepositorio;

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public PostResp addNewTalent(NewTalentReq newTalentRequest) {
        //talentoRepositorio.addNewTalent();
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("ADMIN.SP_ADD_TALENT")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, byte[].class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(8, Number.class, ParameterMode.IN)
                .registerStoredProcedureParameter(9, Number.class, ParameterMode.IN)
                .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(11, Number.class, ParameterMode.OUT)
                .setParameter(1, newTalentRequest.getNombre())
                .setParameter(2, newTalentRequest.getApellidoPaterno())
                .setParameter(3, newTalentRequest.getApellidoMaterno())
                .setParameter(4, newTalentRequest.getFotoDePerfil())
                .setParameter(5, newTalentRequest.getDescripcion())
                .setParameter(6, newTalentRequest.getLinkedin())
                .setParameter(7, newTalentRequest.getGithub())
                .setParameter(8, newTalentRequest.getMontoInicial())
                .setParameter(9, newTalentRequest.getMontoFinal())
                .setParameter(10, newTalentRequest.getCelular());
        storedProcedureQuery.execute();
        Number newTalentoId = (Number) storedProcedureQuery.getSingleResult();
        System.out.println(newTalentoId);
        PostResp temp = new PostResp();
        return temp;
    }
}
