package com.fractal.bancodetalentos.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TalentResp {
    private Integer idTalent;
    private String name;
    private String surname;
    private String secondSurname;
    @Lob
    private byte[] profilePicture;
    private String description;
    private Integer initialSalary;
    private Integer finalSalary;
    private String phone;
    private String linkedin;
    private String github;
    private Date created;
    private Integer avgRating;
    private List<TechnicalAbilitiesResp> technicalAbilities;
    private List<SoftSkillsResp> softSkills;
    private List<WorkExperienceResp> workExperiences;
    private List<EducationalExperienceResp> educationalExperiences;
    private List<LanguageLevelResp> LanguageLevels;
    private List<DocumentResp> documents;
    private List<MasterTalentResp> miscData;
    private List<FeedbackResp> feedbacks;
    private UserListTalentResp userListTalent;

    private String disponibilidad;

}
