package com.fractal.bancodetalentos.service.impl;


import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.Documento;
import com.fractal.bancodetalentos.service.ArchivosService;
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
public class ArchivosServiceImpl implements ArchivosService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Map<String, String> addNewFile(Documento documento, Integer id) {
        Boolean existsTalent = existsTalentId(id);
        if (Boolean.FALSE.equals(existsTalent)) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }
        StoredProcedureQuery storedProcedureDocumentos = entityManager
                .createStoredProcedureQuery("SP_ADD_FILES")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, byte[].class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, documento.getNombre())
                .setParameter(3, documento.getTipoArchivo())
                .setParameter(4, documento.getArchivo());
        storedProcedureDocumentos.execute();

        Map<String, String> resp = new HashMap<>();
        resp.put("id", String.valueOf(id));
        resp.put("message", "CREATED");

        return resp;
    }

    @Override
    public Map<String, String> updateCV(Documento documento, Integer id, Integer idFile) {
        Boolean existsTalent = existsTalentId(id);
        if (Boolean.FALSE.equals(existsTalent)) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }
        Boolean existsFile = existsTalentFile(id, idFile);
        if (Boolean.FALSE.equals(existsFile)) {
            throw new ResourceNotFoundException("File", "id", id);
        }

        StoredProcedureQuery storedProcedureUpdateCv = entityManager
                .createStoredProcedureQuery("SP_EDIT_CV_FILE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, byte[].class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, idFile)
                .setParameter(3, documento.getNombre())
                .setParameter(4, documento.getTipoArchivo())
                .setParameter(5, documento.getArchivo());
        storedProcedureUpdateCv.execute();
        Map<String, String> resp = new HashMap<>();
        resp.put("id", String.valueOf(id));
        resp.put("message", "UPDATE");
        return resp;
    }

    private Boolean existsTalentId(Integer id) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(2);
        return exists == 1;
    }

    private Boolean existsTalentFile(Integer id, Integer idFile) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_FILE_EXISTENCE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT)
                .setParameter(1, id)
                .setParameter(2, idFile);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(3);
        return exists == 1;
    }
}
