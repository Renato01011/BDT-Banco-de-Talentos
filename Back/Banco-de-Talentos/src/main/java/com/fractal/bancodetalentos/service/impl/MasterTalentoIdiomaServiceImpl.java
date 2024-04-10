package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.DuplicatedDataException;
import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.AddLanguageReq;
import com.fractal.bancodetalentos.model.request.UpdateLanguageReq;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.service.MasterTalentoIdiomaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MasterTalentoIdiomaServiceImpl implements MasterTalentoIdiomaService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Map<String, String> addNewLanguage(AddLanguageReq idiomas, Integer id) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }

        StoredProcedureQuery storedProcedureQueryCheckSkill = entityManager.createStoredProcedureQuery("SP_CHECK_LANG_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter(4, Integer.class, ParameterMode.OUT)
                .setParameter(1, id)
                .setParameter(2, idiomas.getIdiomaId());
        storedProcedureQueryCheckSkill.execute();
        Integer existsSkill = (Integer) storedProcedureQueryCheckSkill.getOutputParameterValue(3);

        String idiomaId = String.valueOf(idiomas.getIdiomaId());

        if (existsSkill == 1) {
            throw new DuplicatedDataException(idiomaId);
        }

        StoredProcedureQuery storedProcedureQueryIdioma = entityManager
                .createStoredProcedureQuery("SP_ADD_LANGUAGE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, idiomas.getIdiomaId())
                .setParameter(3, idiomas.getNivelId())
                .setParameter(4, idiomas.getNuEstrellas());
        storedProcedureQueryIdioma.execute();

        Map<String, String> resp = new HashMap<>();
        resp.put("id", String.valueOf(id));
        resp.put("message", "CREATED");

        return resp;
    }

    @Override
    public GeneralResp putLangExp(Integer idTalent, Integer idTalentLang, UpdateLanguageReq idiomas) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, idTalent);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", idTalent);
        }

        StoredProcedureQuery storedProcedureQueryCheckSkill = entityManager.createStoredProcedureQuery("SP_CHECK_LANG_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter(4, Integer.class, ParameterMode.OUT)
                .setParameter(1, idTalent)
                .setParameter(2, idiomas.getIdiomaId());
        storedProcedureQueryCheckSkill.execute();
        Integer existsSkill = (Integer) storedProcedureQueryCheckSkill.getOutputParameterValue(3);
        Integer idExistente = (Integer) storedProcedureQueryCheckSkill.getOutputParameterValue(4);

        String idiomaId = String.valueOf(idiomas.getIdiomaId());

        if (existsSkill == 1 && !idExistente.equals(idTalentLang)) {
            throw new DuplicatedDataException(idiomaId);
        }

        StoredProcedureQuery storedProcedureQueryIdioma = entityManager
                .createStoredProcedureQuery("SP_EDIT_LANGUAGE_LEVEL")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN)
                .setParameter(1, idTalentLang)
                .setParameter(2, idiomas.getIdiomaId())
                .setParameter(3, idiomas.getNivelId())
                .setParameter(4, idiomas.getNuEstrellas());
        storedProcedureQueryIdioma.execute();

        GeneralResp generalResp = new GeneralResp();
        generalResp.setCode(200);
        generalResp.setMessage("Correctly Updated");

        return generalResp;
    }

    @Override
    public GeneralResp deleteTalentLang(Integer idTalent, Integer idTalentLang) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, idTalent);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", idTalent);
        }

        StoredProcedureQuery storedProcedureQueryIdioma = entityManager
                .createStoredProcedureQuery("SP_DELETE_LANGUAGE_LEVEL")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, idTalentLang);
        storedProcedureQueryIdioma.execute();

        GeneralResp generalResp = new GeneralResp();
        generalResp.setCode(200);
        generalResp.setMessage("Correctly Deleted");

        return generalResp;
    }
}
