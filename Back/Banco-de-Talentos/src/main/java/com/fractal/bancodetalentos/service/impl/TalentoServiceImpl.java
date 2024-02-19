package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.model.request.*;
import com.fractal.bancodetalentos.model.response.PostResp;
//import com.fractal.bancodetalentos.repository.BtTmTalentoRepositorio;
import com.fractal.bancodetalentos.service.TalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;

@Service
@RequiredArgsConstructor
public class TalentoServiceImpl implements TalentoService {

    //private final BtTmTalentoRepositorio talentoRepositorio;

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public PostResp addNewTalent(NewTalentReq newTalentRequest) {

        // -- Talento --
        StoredProcedureQuery storedProcedureQueryTalent = entityManager
                .createStoredProcedureQuery("SP_ADD_TALENT")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, byte[].class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(11, Integer.class, ParameterMode.OUT)
                .setParameter(1, newTalentRequest.getNombre())
                .setParameter(2, newTalentRequest.getApellidoPaterno())
                .setParameter(3, newTalentRequest.getApellidoMaterno())
                .setParameter(4, newTalentRequest.getFotoDePerfil())
                .setParameter(5, newTalentRequest.getDescripcion())
                .setParameter(6, newTalentRequest.getMontoInicial())
                .setParameter(7, newTalentRequest.getMontoFinal())
                .setParameter(8, newTalentRequest.getCelular())
                .setParameter(9, newTalentRequest.getLinkedin())
                .setParameter(10, newTalentRequest.getGithub());
        storedProcedureQueryTalent.execute();
        Integer newTalentoId = (Integer) storedProcedureQueryTalent.getOutputParameterValue(11);

        // -- Habilidades Tecnicas --
        if (!newTalentRequest.getHabilidadesTecnicas().isEmpty()) {
            for (HabilidadesTecnicas habilidadesTecnicas: newTalentRequest.getHabilidadesTecnicas()) {
                StoredProcedureQuery storedProcedureQueryHabilidadTecnica = entityManager
                        .createStoredProcedureQuery("SP_ADD_TECHNICAL_ABILITY")
                        .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
                        .setParameter(1, newTalentoId)
                        .setParameter(2, habilidadesTecnicas.getNombre())
                        .setParameter(3, habilidadesTecnicas.getAnios());
                storedProcedureQueryHabilidadTecnica.execute();
            }
        }

        // -- Habilidades Blandas --
        if (!newTalentRequest.getHabilidadesBlandas().isEmpty()) {
            for (HabilidadesBlandas habilidadesBlandas: newTalentRequest.getHabilidadesBlandas()) {
                StoredProcedureQuery storedProcedureQueryHabilidadBlanda = entityManager
                        .createStoredProcedureQuery("SP_ADD_SOFT_SKILL")
                        .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                        .setParameter(1, newTalentoId)
                        .setParameter(2, habilidadesBlandas.getNombre());
                storedProcedureQueryHabilidadBlanda.execute();
            }
        }

        // -- Experiencias Laborales --
        if (!newTalentRequest.getExperienciasLaborales().isEmpty()) {
            for (ExperienciasLaborales experienciasLaborales : newTalentRequest.getExperienciasLaborales()) {
                StoredProcedureQuery storedProcedureQueryExperienciasLaborales = entityManager
                        .createStoredProcedureQuery("SP_ADD_WORK_EXPERIENCE")
                        .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(4, Date.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(5, Date.class, ParameterMode.IN)
                        .setParameter(1, newTalentoId)
                        .setParameter(2, experienciasLaborales.getEmpresa())
                        .setParameter(3, experienciasLaborales.getPuesto())
                        .setParameter(4, experienciasLaborales.getFechaInicio())
                        .setParameter(5, experienciasLaborales.getFechaFin());
                storedProcedureQueryExperienciasLaborales.execute();
            }
        }

        // -- Experiencias Educativas --
        if (!newTalentRequest.getExperienciasEducativas().isEmpty()) {
            for (ExperienciasEducativas experienciasEducativas : newTalentRequest.getExperienciasEducativas()) {
                StoredProcedureQuery storedProcedureQueryExperienciasEducativas = entityManager
                        .createStoredProcedureQuery("SP_ADD_EDUCATIONAL_EXPERIENCE")
                        .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(5, Date.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(6, Date.class, ParameterMode.IN)
                        .setParameter(1, newTalentoId)
                        .setParameter(2, experienciasEducativas.getInstitucion())
                        .setParameter(3, experienciasEducativas.getCarrera())
                        .setParameter(4, experienciasEducativas.getGrado())
                        .setParameter(5, experienciasEducativas.getFechaInicio())
                        .setParameter(6, experienciasEducativas.getFechaFin());
                storedProcedureQueryExperienciasEducativas.execute();
            }
        }

        // -- Idiomas --
        if (!newTalentRequest.getIdiomas().isEmpty()) {
            for (Idiomas idioma : newTalentRequest.getIdiomas()) {
                StoredProcedureQuery storedProcedureQueryIdioma = entityManager
                        .createStoredProcedureQuery("SP_ADD_LANGUAGE")
                        .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN)
                        .setParameter(1, newTalentoId)
                        .setParameter(2, idioma.getIdiomaId())
                        .setParameter(3, idioma.getNivelId())
                        .setParameter(4, idioma.getNuEstrellas());
                storedProcedureQueryIdioma.execute();
            }
        }

        // -- Documentos --
        if (!newTalentRequest.getDocumentos().isEmpty()) {
            for (Documento documento : newTalentRequest.getDocumentos()) {
                StoredProcedureQuery storedProcedureDocumentos = entityManager
                        .createStoredProcedureQuery("SP_ADD_FILES")
                        .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(4, byte[].class, ParameterMode.IN)
                        .setParameter(1, newTalentoId)
                        .setParameter(2, documento.getNombre())
                        .setParameter(3, documento.getTipoArchivo())
                        .setParameter(4, documento.getArchivo());
                storedProcedureDocumentos.execute();
            }
        }

        // -- Pais --
        StoredProcedureQuery storedProcedureQueryPais = entityManager
                .createStoredProcedureQuery("SP_ADD_COUNTRY")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, newTalentoId)
                .setParameter(2, newTalentRequest.getIdPais());
        storedProcedureQueryPais.execute();

        // -- Ciudad --
        StoredProcedureQuery storedProcedureQueryCiudad = entityManager
                .createStoredProcedureQuery("SP_ADD_CITY")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, newTalentoId)
                .setParameter(2, newTalentRequest.getIdCiudad());
        storedProcedureQueryCiudad.execute();

        // -- Perfil --
        StoredProcedureQuery storedProcedureQueryPerfil = entityManager
                .createStoredProcedureQuery("SP_ADD_PROFILE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, newTalentoId)
                .setParameter(2, newTalentRequest.getIdPuestoActual());
        storedProcedureQueryPerfil.execute();

        PostResp temp = new PostResp();
        temp.setCode(200);
        temp.setMessage("Correctly Added");
        return temp;
    }
}
