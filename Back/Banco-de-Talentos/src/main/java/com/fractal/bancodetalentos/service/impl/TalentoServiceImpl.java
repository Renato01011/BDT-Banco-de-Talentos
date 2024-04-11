package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.dto.*;
import com.fractal.bancodetalentos.model.request.*;
import com.fractal.bancodetalentos.model.response.*;
import com.fractal.bancodetalentos.service.TalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TalentoServiceImpl implements TalentoService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public GeneralResp addNewTalent(NewTalentReq newTalentRequest) {

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
                .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(13, Integer.class, ParameterMode.OUT)
                .setParameter(1, newTalentRequest.getNombre())
                .setParameter(2, newTalentRequest.getApellidoPaterno())
                .setParameter(3, newTalentRequest.getApellidoMaterno())
                .setParameter(4, newTalentRequest.getFotoDePerfil())
                .setParameter(5, newTalentRequest.getDescripcion())
                .setParameter(6, newTalentRequest.getMontoInicial())
                .setParameter(7, newTalentRequest.getMontoFinal())
                .setParameter(8, newTalentRequest.getCelular())
                .setParameter(9, newTalentRequest.getLinkedin())
                .setParameter(10, newTalentRequest.getGithub())
                .setParameter(11, newTalentRequest.getDisponibilidad())
                .setParameter(12, newTalentRequest.getEmail());

        storedProcedureQueryTalent.execute();
        Integer newTalentoId = (Integer) storedProcedureQueryTalent.getOutputParameterValue(13);

        // -- Habilidades Tecnicas --
        if (!newTalentRequest.getHabilidadesTecnicas().isEmpty()) {
            for (HabilidadesTecnicasDTO habilidadesTecnicasDTO : newTalentRequest.getHabilidadesTecnicas()) {
                StoredProcedureQuery storedProcedureQueryHabilidadTecnica = entityManager
                        .createStoredProcedureQuery("SP_ADD_TECHNICAL_ABILITY")
                        .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(3, BigDecimal.class, ParameterMode.IN)
                        .setParameter(1, newTalentoId)
                        .setParameter(2, habilidadesTecnicasDTO.getNombre())
                        .setParameter(3, habilidadesTecnicasDTO.getAnios());
                storedProcedureQueryHabilidadTecnica.execute();
            }
        }

        // -- Habilidades Blandas --
        if (!newTalentRequest.getHabilidadesBlandas().isEmpty()) {
            for (HabilidadesBlandasDTO habilidadesBlandasDTO : newTalentRequest.getHabilidadesBlandas()) {
                StoredProcedureQuery storedProcedureQueryHabilidadBlanda = entityManager
                        .createStoredProcedureQuery("SP_ADD_SOFT_SKILL")
                        .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                        .setParameter(1, newTalentoId)
                        .setParameter(2, habilidadesBlandasDTO.getNombre());
                storedProcedureQueryHabilidadBlanda.execute();
            }
        }

        // -- Experiencias Laborales --
        if (!newTalentRequest.getExperienciasLaborales().isEmpty()) {
            for (ExperienciasLaboralesDTO experienciasLaboralesDTO : newTalentRequest.getExperienciasLaborales()) {
                StoredProcedureQuery storedProcedureQueryExperienciasLaborales = entityManager
                        .createStoredProcedureQuery("SP_ADD_WORK_EXPERIENCE")
                        .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(4, Date.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(5, Date.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN)
                        .setParameter(1, newTalentoId)
                        .setParameter(2, experienciasLaboralesDTO.getEmpresa())
                        .setParameter(3, experienciasLaboralesDTO.getPuesto())
                        .setParameter(4, experienciasLaboralesDTO.getFechaInicio())
                        .setParameter(5, experienciasLaboralesDTO.getFechaFin())
                        .setParameter(6, experienciasLaboralesDTO.getFlActualidad());
                storedProcedureQueryExperienciasLaborales.execute();
            }
        }

        // -- Experiencias Educativas --
        if (!newTalentRequest.getExperienciasEducativas().isEmpty()) {
            for (ExperienciasEducativasDTO experienciasEducativasDTO : newTalentRequest.getExperienciasEducativas()) {
                StoredProcedureQuery storedProcedureQueryExperienciasEducativas = entityManager
                        .createStoredProcedureQuery("SP_ADD_EDUCATIONAL_EXPERIENCE")
                        .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(5, Date.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(6, Date.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN)
                        .setParameter(1, newTalentoId)
                        .setParameter(2, experienciasEducativasDTO.getInstitucion())
                        .setParameter(3, experienciasEducativasDTO.getCarrera())
                        .setParameter(4, experienciasEducativasDTO.getGrado())
                        .setParameter(5, experienciasEducativasDTO.getFechaInicio())
                        .setParameter(6, experienciasEducativasDTO.getFechaFin())
                        .setParameter(7, experienciasEducativasDTO.getFlActualidad());
                storedProcedureQueryExperienciasEducativas.execute();
            }
        }

        // -- Idiomas --
        if (!newTalentRequest.getIdiomas().isEmpty()) {
            for (IdiomasDTO idioma : newTalentRequest.getIdiomas()) {
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
            for (DocumentoDTO documentoDTO : newTalentRequest.getDocumentos()) {
                StoredProcedureQuery storedProcedureDocumentos = entityManager
                        .createStoredProcedureQuery("SP_ADD_FILES")
                        .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(4, byte[].class, ParameterMode.IN)
                        .setParameter(1, newTalentoId)
                        .setParameter(2, documentoDTO.getNombre())
                        .setParameter(3, documentoDTO.getTipoArchivo())
                        .setParameter(4, documentoDTO.getArchivo());
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

        // -- Tipo Moneda --
        StoredProcedureQuery storedProcedureQueryMoneda = entityManager
                .createStoredProcedureQuery("SP_ADD_COIN")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, newTalentoId)
                .setParameter(2, newTalentRequest.getIdTipoMoneda());
        storedProcedureQueryMoneda.execute();

        GeneralResp temp = new GeneralResp();
        temp.setCode(200);
        temp.setMessage("Correctly Added");
        return temp;
    }

    @Override
    public TalentResp getTalent(Integer id, GetTalentReq getTalentReq) {
        StoredProcedureQuery storedProcedureQueryCheckUser = entityManager.createStoredProcedureQuery("SP_CHECK_USER_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, getTalentReq.getUserId());
        storedProcedureQueryCheckUser.execute();
        Integer exists = (Integer) storedProcedureQueryCheckUser.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("User", "id", getTalentReq.getUserId());
        }

        // -- General Info --
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_GET_TALENT")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, id);
        storedProcedureQuery.execute();
        List<Object[]> generalInfo = storedProcedureQuery.getResultList();

        // -- Technical Abilities --
        StoredProcedureQuery storedProcedureQueryTechnicalAbilities = entityManager
                .createStoredProcedureQuery("SP_GET_TALENT_TECHNICAL_ABILITY")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, id);
        storedProcedureQueryTechnicalAbilities.execute();
        List<Object[]> technicalAbilities = storedProcedureQueryTechnicalAbilities.getResultList();

        // -- Soft Skills --
        StoredProcedureQuery storedProcedureQuerySoftSkills = entityManager
                .createStoredProcedureQuery("SP_GET_TALENT_SOFT_SKILLS")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, id);
        storedProcedureQuerySoftSkills.execute();
        List<Object[]> softSkills = storedProcedureQuerySoftSkills.getResultList();

        // -- Work Experience --
        StoredProcedureQuery storedProcedureQueryWorkExperience = entityManager
                .createStoredProcedureQuery("SP_GET_TALENT_WORK_EXPERIENCE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, id);
        storedProcedureQueryWorkExperience.execute();
        List<Object[]> workExperience = storedProcedureQueryWorkExperience.getResultList();

        // -- Educational Experience --
        StoredProcedureQuery storedProcedureQueryEducationalExperience = entityManager
                .createStoredProcedureQuery("SP_GET_TALENT_EDUCATIONAL_EXPERIENCE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, id);
        storedProcedureQueryEducationalExperience.execute();
        List<Object[]> educationalExperience = storedProcedureQueryEducationalExperience.getResultList();

        // -- Language Proficiency --
        StoredProcedureQuery storedProcedureQueryLanguageProficiency = entityManager
                .createStoredProcedureQuery("SP_GET_TALENT_LANGUAGE_PROFICIENCY")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, id);
        storedProcedureQueryLanguageProficiency.execute();
        List<Object[]> languageProficiency = storedProcedureQueryLanguageProficiency.getResultList();

        // -- Documents --
        StoredProcedureQuery storedProcedureQueryDocuments = entityManager
                .createStoredProcedureQuery("SP_GET_TALENT_DOCUMENTS")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, id);
        storedProcedureQueryDocuments.execute();
        List<Object[]> documents = storedProcedureQueryDocuments.getResultList();

        // -- Master Talent --
        StoredProcedureQuery storedProcedureQueryMasterTalent = entityManager
                .createStoredProcedureQuery("SP_GET_TALENT_MASTER")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, id);
        storedProcedureQueryMasterTalent.execute();
        List<Object[]> masterTalent = storedProcedureQueryMasterTalent.getResultList();

        // -- Feedbacks --
        StoredProcedureQuery storedProcedureQueryFeedbacks = entityManager
                .createStoredProcedureQuery("SP_GET_TALENT_FEEDBACKS")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, id);
        storedProcedureQueryFeedbacks.execute();
        List<Object[]> feedbacks = storedProcedureQueryFeedbacks.getResultList();

        // -- User Lists --
        StoredProcedureQuery storedProcedureQueryUserList = entityManager
                .createStoredProcedureQuery("SP_GET_TALENT_USER_LIST")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, getTalentReq.getUserId());
        storedProcedureQueryUserList.execute();
        List<Object[]> userListTalent = storedProcedureQueryUserList.getResultList();

        // -- -- Filling Response -- --
        TalentResp talentResp = new TalentResp();

        // -- General Info --
        for (Object[] objects : generalInfo) {
            talentResp.setIdTalent((Integer) objects[0]);
            talentResp.setName((String) objects[1]);
            talentResp.setSurname((String) objects[2]);
            talentResp.setSecondSurname((String) objects[3]);
            talentResp.setProfilePicture((byte[]) objects[4]);
            talentResp.setDescription((String) objects[5]);
            talentResp.setInitialSalary((Integer) objects[6]);
            talentResp.setFinalSalary((Integer) objects[7]);
            talentResp.setPhone((String) objects[8]);
            talentResp.setLinkedin((String) objects[9]);
            talentResp.setGithub((String) objects[10]);
            talentResp.setCreated((Date) objects[11]);
            talentResp.setAvgRating((Integer) objects[12]);
            talentResp.setDisponibilidad((String) objects[13]);
            talentResp.setEmail((String) objects[14]);
        }

        // -- Technical Abilities --
        List<TechnicalAbilitiesResp> technicalAbilitiesList = new ArrayList<>();
        for (Object[] objects : technicalAbilities) {
            TechnicalAbilitiesResp technicalAbility = new TechnicalAbilitiesResp();
            technicalAbility.setIdTechnicalAbility((Integer) objects[0]);
            technicalAbility.setName((String) objects[1]);
            technicalAbility.setYears((BigDecimal) objects[2]);
            technicalAbilitiesList.add(technicalAbility);
        }
        talentResp.setTechnicalAbilities(technicalAbilitiesList);

        // -- Soft Skills --
        List<SoftSkillsResp> softSkillsList = new ArrayList<>();
        for (Object[] objects : softSkills) {
            SoftSkillsResp softSkill = new SoftSkillsResp();
            softSkill.setIdSoftSkill((Integer) objects[0]);
            softSkill.setName((String) objects[1]);
            softSkillsList.add(softSkill);
        }
        talentResp.setSoftSkills(softSkillsList);

        // -- Work Experience --
        List<WorkExperienceResp> workExperiencesList = new ArrayList<>();
        for (Object[] objects : workExperience) {
            WorkExperienceResp workExperienceTemp = new WorkExperienceResp();
            workExperienceTemp.setIdWorkExperience((Integer) objects[0]);
            workExperienceTemp.setFirm((String) objects[1]);
            workExperienceTemp.setJobTitle((String) objects[2]);
            workExperienceTemp.setIntialDate((Date) objects[3]);
            workExperienceTemp.setFinalDate((Date) objects[4]);
            workExperienceTemp.setFlActualidad((Integer) objects[5]);
            workExperiencesList.add(workExperienceTemp);
        }
        talentResp.setWorkExperiences(workExperiencesList);

        // -- Educational Experience --
        List<EducationalExperienceResp> educationalExperiencesList = new ArrayList<>();
        for (Object[] objects : educationalExperience) {
            EducationalExperienceResp educationalExperienceTemp = new EducationalExperienceResp();
            educationalExperienceTemp.setIdEducationalExperience((Integer) objects[0]);
            educationalExperienceTemp.setInstitution((String) objects[1]);
            educationalExperienceTemp.setMajor((String) objects[2]);
            educationalExperienceTemp.setDegree((String) objects[3]);
            educationalExperienceTemp.setInitialDate((Date) objects[4]);
            educationalExperienceTemp.setFinalDate((Date) objects[5]);
            educationalExperienceTemp.setFlActualidad((Integer) objects[6]);
            educationalExperiencesList.add(educationalExperienceTemp);
        }
        talentResp.setEducationalExperiences(educationalExperiencesList);

        // -- Language Proficiency --
        List<LanguageLevelResp> languageLevelList = new ArrayList<>();
        for (Object[] objects : languageProficiency) {
            LanguageLevelResp languageLevel = new LanguageLevelResp();
            languageLevel.setIdTalentLanguage((Integer) objects[0]);
            languageLevel.setIdLanguage((Integer) objects[1]);
            languageLevel.setLanguageName((String) objects[2]);
            languageLevel.setLanguageCode((String) objects[3]);
            languageLevel.setIdProficiency((Integer) objects[4]);
            languageLevel.setProficiency((String) objects[5]);
            languageLevel.setStarCount((Integer) objects[6]);
            languageLevelList.add(languageLevel);
        }
        talentResp.setLanguageLevels(languageLevelList);

        // -- Documents --
        List<DocumentResp> documentsList = new ArrayList<>();
        for (Object[] objects : documents) {
            DocumentResp document = new DocumentResp();
            document.setIdDocument((Integer) objects[0]);
            document.setDocumentName((String) objects[1]);
            document.setDocumentType((String) objects[2]);
            document.setDocument((byte[]) objects[3]);
            documentsList.add(document);
        }
        talentResp.setDocuments(documentsList);

        // -- Master Talent --
        List<MasterTalentResp> masterTalentList = new ArrayList<>();
        for (Object[] objects : masterTalent) {
            MasterTalentResp masterTalentTemp = new MasterTalentResp();
            masterTalentTemp.setIdMasterTalent((Integer) objects[0]);
            masterTalentTemp.setIdMaster((Integer) objects[1]);
            masterTalentTemp.setId((Integer) objects[2]);
            masterTalentTemp.setName((String) objects[3]);
            masterTalentTemp.setDescription((String) objects[4]);
            masterTalentTemp.setCode((String) objects[5]);
            masterTalentTemp.setSecondCode((String) objects[6]);
            masterTalentList.add(masterTalentTemp);
        }
        talentResp.setMiscData(masterTalentList);

        // -- Feedbacks --
        List<FeedbackResp> feedbackList = new ArrayList<>();
        for (Object[] objects : feedbacks) {
            FeedbackResp feedback = new FeedbackResp();
            feedback.setIdFeedback((Integer) objects[0]);
            feedback.setStarCount((Integer) objects[1]);
            feedback.setDescription((String) objects[2]);
            feedback.setIdUserFrom((Integer) objects[3]);
            feedback.setUserFromName((String) objects[4]);
            feedback.setUserFromPhoto((byte[]) objects[5]);
            feedbackList.add(feedback);
        }
        talentResp.setFeedbacks(feedbackList);

        // -- User Lists --
        if (!userListTalent.isEmpty()) {
            UserListTalentResp userListTalentResp = new UserListTalentResp();
            for (Object[] objects : userListTalent) {
                userListTalentResp.setIdListUser((Integer) objects[0]);
                userListTalentResp.setListName((String) objects[1]);
                userListTalentResp.setCreated((Date) objects[2]);
                userListTalentResp.setIdListUserTalent((Integer) objects[3]);
            }
            talentResp.setUserListTalent(userListTalentResp);
        } else {
            talentResp.setUserListTalent(null);
        }

        return talentResp;
    }

    @Override
    public GeneralResp putDescription(Integer id, UpdateDescriptionReq updateDescriptionReq) {
        StoredProcedureQuery storedProcedureQueryCheckTalent = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQueryCheckTalent.execute();
        Integer exists = (Integer) storedProcedureQueryCheckTalent.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }

        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_EDIT_DESCRIPTION")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, updateDescriptionReq.getDescription());
        storedProcedureQuery.execute();

        GeneralResp temp = new GeneralResp();
        temp.setCode(200);
        temp.setMessage("Correctly Updated");
        return temp;
    }

    @Override
    public GeneralResp putProfilePicture(Integer id, UpdateProfilePictureReq updateProfilePictureReq) {
        StoredProcedureQuery storedProcedureQueryCheckTalent = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQueryCheckTalent.execute();
        Integer exists = (Integer) storedProcedureQueryCheckTalent.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }

        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_EDIT_PROFILE_PICTURE")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, byte[].class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, updateProfilePictureReq.getProfilePicture());
        storedProcedureQuery.execute();

        GeneralResp temp = new GeneralResp();
        temp.setCode(200);
        temp.setMessage("Correctly Updated");
        return temp;
    }

    @Override
    public GeneralResp putSalary(Integer id, UpdateSalaryReq updateSalaryReq) {
        StoredProcedureQuery storedProcedureQueryCheckTalent = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQueryCheckTalent.execute();
        Integer exists = (Integer) storedProcedureQueryCheckTalent.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }

        StoredProcedureQuery storedProcedureQueryCoin = entityManager
                .createStoredProcedureQuery("SP_EDIT_COIN")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, updateSalaryReq.getIdCoin());
        storedProcedureQueryCoin.execute();

        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_EDIT_SALARY")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, updateSalaryReq.getInitialSalary())
                .setParameter(3, updateSalaryReq.getFinalSalary());
        storedProcedureQuery.execute();

        GeneralResp temp = new GeneralResp();
        temp.setCode(200);
        temp.setMessage("Correctly Updated");
        return temp;
    }

    @Override
    public GeneralResp putSocialLinks(Integer id, UpdateSocialLinksReq updateSocialLinksReq) {
        StoredProcedureQuery storedProcedureQueryCheckTalent = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQueryCheckTalent.execute();
        Integer exists = (Integer) storedProcedureQueryCheckTalent.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }

        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_EDIT_SOCIAL_LINKS")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, updateSocialLinksReq.getLinkedin())
                .setParameter(3, updateSocialLinksReq.getGithub());
        storedProcedureQuery.execute();

        GeneralResp temp = new GeneralResp();
        temp.setCode(200);
        temp.setMessage("Correctly Updated");
        return temp;
    }

    @Override
    public GeneralResp putAvailability(Integer id, UpdateDisponibilidadReq updateDisponibilidadReq) {
        StoredProcedureQuery storedProcedureQueryCheckTalent = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQueryCheckTalent.execute();
        Integer exists = (Integer) storedProcedureQueryCheckTalent.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }

        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_EDIT_AVAILABILITY")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, updateDisponibilidadReq.getDisponibilidad());
        storedProcedureQuery.execute();

        GeneralResp temp = new GeneralResp();
        temp.setCode(200);
        temp.setMessage("Correctly Updated");
        return temp;
    }

    @Override
    public GeneralResp updateContactInfo(Integer id, UpdateContactInfoReq updateContactInfoReq) {

        boolean existsTalent = existsTalentId(id);
        if (!existsTalent) {
            throw new ResourceNotFoundException("Talent", "id", id);
        }
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("SP_EDIT_CONTACT_INFO")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, updateContactInfoReq.getCelular())
                .setParameter(3, updateContactInfoReq.getEmail());

        storedProcedureQuery.execute();

        GeneralResp temp = new GeneralResp();
        temp.setCode(200);
        temp.setMessage("Correctly Updated");
        return temp;
    }

    private boolean existsTalentId(Integer id) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SP_CHECK_TALENT_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, id);
        storedProcedureQuery.execute();
        Integer exists = (Integer) storedProcedureQuery.getOutputParameterValue(2);
        return exists == 1;
    }
}
