package com.fractal.bancodetalentos.service;

import com.fractal.bancodetalentos.model.request.*;
import com.fractal.bancodetalentos.model.response.GeneralResp;
import com.fractal.bancodetalentos.model.response.TalentResp;

public interface TalentoService {

    GeneralResp addNewTalent(NewTalentReq newTalentRequest);
    TalentResp getTalent(Integer id, GetTalentReq getTalentReq);
    GeneralResp putDescription(Integer id, UpdateDescriptionReq updateDescriptionReq);
    GeneralResp putProfilePicture(Integer id, UpdateProfilePictureReq updateProfilePictureReq);
    GeneralResp putSalary(Integer id, UpdateSalaryReq updateSalaryReq);
    GeneralResp putSocialLinks(Integer id, UpdateSocialLinksReq updateSocialLinksReq);

}
