package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.*;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.model.response.TalentResp;

public interface TalentoService {

    GeneralResp addNewTalent(NewTalentReq newTalentRequest);
    TalentResp getTalent(Integer id);
    GeneralResp putDescription(Integer id, DescriptionReq descriptionReq);
    GeneralResp putProfilePicture(Integer id, ProfilePictureReq profilePictureReq);
    GeneralResp putSalary(Integer id, SalaryReq salaryReq);
    GeneralResp putSocialLinks(Integer id, SocialLinksReq socialLinksReq);

}
