package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.dto.*;
import com.fractal.bancodetalentos.model.request.*;
import com.fractal.bancodetalentos.model.response.*;
import com.fractal.bancodetalentos.service.TalentoService;
import com.fractal.bancodetalentos.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.procedure.ParameterRegistration;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static com.fractal.bancodetalentos.util.ValidationUtil.setNonNullValue;

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
                .registerStoredProcedureParameter(8, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(9, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(16, Integer.class, ParameterMode.OUT)
                .setParameter(1, newTalentRequest.getNombre())
                .setParameter(2, newTalentRequest.getApellidoPaterno())
                .setParameter(3, newTalentRequest.getApellidoMaterno())
                .setParameter(4, newTalentRequest.getFotoDePerfil()!=null?newTalentRequest.getFotoDePerfil():new byte[0])
                .setParameter(5, newTalentRequest.getDescripcion())
                .setParameter(6, newTalentRequest.getMontoInicialPlanilla())
                .setParameter(7, newTalentRequest.getMontoFinalPlanilla())
                .setParameter(8, newTalentRequest.getMontoInicialRxH())
                .setParameter(9, newTalentRequest.getMontoFinalRxh())
                .setParameter(10, newTalentRequest.getCelular())
                .setParameter(11, newTalentRequest.getLinkedin())
                .setParameter(12, newTalentRequest.getGithub())
                .setParameter(13, newTalentRequest.getDisponibilidad())
                .setParameter(14, newTalentRequest.getEmail())
                .setParameter(15, newTalentRequest.getPuesto());

        storedProcedureQueryTalent.execute();
        Integer newTalentoId = (Integer) storedProcedureQueryTalent.getOutputParameterValue(16);

        // -- Habilidades Tecnicas --
        if (newTalentRequest.getHabilidadesTecnicas()!=null && !newTalentRequest.getHabilidadesTecnicas().isEmpty()) {
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
        if (newTalentRequest.getHabilidadesBlandas()!=null && !newTalentRequest.getHabilidadesBlandas().isEmpty()) {
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
        if (newTalentRequest.getExperienciasLaborales()!=null && !newTalentRequest.getExperienciasLaborales().isEmpty() && ValidationUtil.allFieldsValid(newTalentRequest.getExperienciasLaborales())) {
            for (ExperienciasLaboralesDTO experienciasLaboralesDTO : newTalentRequest.getExperienciasLaborales()) {
                StoredProcedureQuery storedProcedureQueryExperienciasLaborales = entityManager
                        .createStoredProcedureQuery("SP_ADD_WORK_EXPERIENCE")
                        .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(4, Date.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(5, Date.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                        .setParameter(1, newTalentoId)
                        .setParameter(2, experienciasLaboralesDTO.getEmpresa())
                        .setParameter(3, experienciasLaboralesDTO.getPuesto())
                        .setParameter(4, experienciasLaboralesDTO.getFechaInicio())
                        .setParameter(5, experienciasLaboralesDTO.getFechaFin())
                        .setParameter(6, experienciasLaboralesDTO.getFlActualidad())
                        .setParameter(7, experienciasLaboralesDTO.getFunctions());
                storedProcedureQueryExperienciasLaborales.execute();
            }
        }

        // -- Experiencias Educativas --
        if (newTalentRequest.getExperienciasEducativas()!=null && !newTalentRequest.getExperienciasEducativas().isEmpty() && ValidationUtil.allFieldsValid(newTalentRequest.getExperienciasEducativas())) {
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
        if (newTalentRequest.getIdiomas()!=null && !newTalentRequest.getIdiomas().isEmpty()) {
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
        if (newTalentRequest.getDocumentos()!=null && !newTalentRequest.getDocumentos().isEmpty() && ValidationUtil.allFieldsValid(newTalentRequest.getDocumentos())) {
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

        // -- Icon coin--
        StoredProcedureQuery storedProcedureQueryCoinIcon = entityManager
                .createStoredProcedureQuery("SP_GET_COIN_ICON")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, id);

        storedProcedureQueryCoinIcon.execute();
        List<String> coinIcon = storedProcedureQueryCoinIcon.getResultList();

        // -- -- Filling Response -- --
        TalentResp talentResp = new TalentResp();

        // -- General Info --
        for (Object[] objects : generalInfo) {
            setNonNullValue(objects[0], talentResp::setIdTalent);
            setNonNullValue(objects[1], talentResp::setName);
            setNonNullValue(objects[2], talentResp::setSurname);
            setNonNullValue(objects[3], talentResp::setSecondSurname);
            setNonNullValue(objects[4], talentResp::setProfilePicture);
            setNonNullValue(objects[5], talentResp::setDescription);
            setNonNullValue(objects[6], talentResp::setInitialSalaryPlanilla);
            setNonNullValue(objects[7], talentResp::setFinalSalaryPlanilla);
            setNonNullValue(objects[8], talentResp::setPhone);
            setNonNullValue(objects[9], talentResp::setLinkedin);
            setNonNullValue(objects[10], talentResp::setGithub);
            setNonNullValue(objects[11], talentResp::setCreated);
            setNonNullValue(objects[12], talentResp::setAvgRating);
            setNonNullValue(objects[13], talentResp::setDisponibilidad);
            setNonNullValue(objects[14], talentResp::setEmail);
            setNonNullValue(objects[15], talentResp::setInitialSalaryRxH);
            setNonNullValue(objects[16], talentResp::setFinalSalaryRxH);
            setNonNullValue(objects[17], talentResp::setPuesto);
        }

        // -- Technical Abilities --
        List<TechnicalAbilitiesResp> technicalAbilitiesList = new ArrayList<>();
        for (Object[] objects : technicalAbilities) {
            TechnicalAbilitiesResp technicalAbility = new TechnicalAbilitiesResp();
            setNonNullValue(objects[0], technicalAbility::setIdTechnicalAbility);
            setNonNullValue(objects[1], technicalAbility::setName);
            setNonNullValue(objects[2], (value) -> technicalAbility.setYears(value != null ? (BigDecimal) value : null));
            technicalAbilitiesList.add(technicalAbility);
        }
        talentResp.setTechnicalAbilities(technicalAbilitiesList);

        // -- Soft Skills --
        List<SoftSkillsResp> softSkillsList = new ArrayList<>();
        for (Object[] objects : softSkills) {
            SoftSkillsResp softSkill = new SoftSkillsResp();
            setNonNullValue(objects[0], softSkill::setIdSoftSkill);
            setNonNullValue(objects[1], softSkill::setName);
            softSkillsList.add(softSkill);
        }
        talentResp.setSoftSkills(softSkillsList);

        // -- Work Experience --
        List<WorkExperienceResp> workExperiencesList = new ArrayList<>();
        for (Object[] objects : workExperience) {
            WorkExperienceResp workExperienceTemp = new WorkExperienceResp();
            setNonNullValue(objects[0], workExperienceTemp::setIdWorkExperience);
            setNonNullValue(objects[1], workExperienceTemp::setFirm);
            setNonNullValue(objects[2], workExperienceTemp::setJobTitle);
            setNonNullValue(objects[3], workExperienceTemp::setIntialDate);
            setNonNullValue(objects[4], workExperienceTemp::setFinalDate);
            setNonNullValue(objects[5], workExperienceTemp::setFlActualidad);
            setNonNullValue(objects[6], workExperienceTemp::setFunctions);
            workExperiencesList.add(workExperienceTemp);
        }
        talentResp.setWorkExperiences(workExperiencesList);

        // -- Educational Experience --
        List<EducationalExperienceResp> educationalExperiencesList = new ArrayList<>();
        for (Object[] objects : educationalExperience) {
            EducationalExperienceResp educationalExperienceTemp = new EducationalExperienceResp();
            setNonNullValue(objects[0], educationalExperienceTemp::setIdEducationalExperience);
            setNonNullValue(objects[1], educationalExperienceTemp::setInstitution);
            setNonNullValue(objects[2], educationalExperienceTemp::setMajor);
            setNonNullValue(objects[3], educationalExperienceTemp::setDegree);
            setNonNullValue(objects[4], educationalExperienceTemp::setInitialDate);
            setNonNullValue(objects[5], educationalExperienceTemp::setFinalDate);
            setNonNullValue(objects[6], educationalExperienceTemp::setFlActualidad);
            educationalExperiencesList.add(educationalExperienceTemp);
        }
        talentResp.setEducationalExperiences(educationalExperiencesList);

        // -- Language Proficiency --
        List<LanguageLevelResp> languageLevelList = new ArrayList<>();
        for (Object[] objects : languageProficiency) {
            LanguageLevelResp languageLevel = new LanguageLevelResp();
            setNonNullValue(objects[0], languageLevel::setIdTalentLanguage);
            setNonNullValue(objects[1], languageLevel::setIdLanguage);
            setNonNullValue(objects[2], languageLevel::setLanguageName);
            setNonNullValue(objects[3], languageLevel::setLanguageCode);
            setNonNullValue(objects[4], languageLevel::setIdProficiency);
            setNonNullValue(objects[5], languageLevel::setProficiency);
            setNonNullValue(objects[6], languageLevel::setStarCount);
            languageLevelList.add(languageLevel);
        }
        talentResp.setLanguageLevels(languageLevelList);

        // -- Documents --
        List<DocumentResp> documentsList = new ArrayList<>();
        for (Object[] objects : documents) {
            DocumentResp document = new DocumentResp();
            setNonNullValue(objects[0], document::setIdDocument);
            setNonNullValue(objects[1], document::setDocumentName);
            setNonNullValue(objects[2], document::setDocumentType);
            setNonNullValue(objects[3], document::setDocument);
            documentsList.add(document);
        }
        talentResp.setDocuments(documentsList);

        // -- Master Talent --
        List<MasterTalentResp> masterTalentList = new ArrayList<>();
        for (Object[] objects : masterTalent) {
            MasterTalentResp masterTalentTemp = new MasterTalentResp();
            setNonNullValue(objects[0], masterTalentTemp::setIdMasterTalent);
            setNonNullValue(objects[1], masterTalentTemp::setIdMaster);
            setNonNullValue(objects[2], masterTalentTemp::setId);
            setNonNullValue(objects[3], masterTalentTemp::setName);
            setNonNullValue(objects[4], masterTalentTemp::setDescription);
            setNonNullValue(objects[5], masterTalentTemp::setCode);
            setNonNullValue(objects[6], masterTalentTemp::setSecondCode);
            masterTalentList.add(masterTalentTemp);
        }
        talentResp.setMiscData(masterTalentList);

        // -- Feedbacks --
        List<FeedbackResp> feedbackList = new ArrayList<>();
        for (Object[] objects : feedbacks) {
            FeedbackResp feedback = new FeedbackResp();
            setNonNullValue(objects[0], feedback::setIdFeedback);
            setNonNullValue(objects[1], feedback::setStarCount);
            setNonNullValue(objects[2], feedback::setDescription);
            setNonNullValue(objects[3], feedback::setIdUserFrom);
            setNonNullValue(objects[4], feedback::setUserFromName);
            setNonNullValue(objects[5], feedback::setUserFromPhoto);
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

        // -- Coin Icon --
        if(!coinIcon.isEmpty() && coinIcon!=null){
            for(String coin : coinIcon){
                talentResp.setIconCoin(coin);
            }
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
                .registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN)
                .setParameter(1, id)
                .setParameter(2, updateSalaryReq.getInitialSalaryPlanilla())
                .setParameter(3, updateSalaryReq.getFinalSalaryPlanilla())
                .setParameter(4, updateSalaryReq.getInitialSalaryRxH())
                .setParameter(5, updateSalaryReq.getFinalSalaryRxH());
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
