import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import * as EditModels from '../../models/interfaces/editinfo.interfaces';
import { UrlConstants } from 'src/app/core/global/constants/url.constants';

@Injectable({
  providedIn: 'root',
})
export class EditInfoService {
  constructor(private httpClient: HttpClient) {}

  public editTalentDescription(body: EditModels.EditDescription, id: number): Observable<EditModels.EditResp> {
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_DESCRIPTION}/${id}`,
      body
    );
  }

  public editTalentProfilePicture(body: EditModels.EditProfilePicture, id: number): Observable<EditModels.EditResp> {
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_PROFILE_PICTURE}/${id}`,
      body
    );
  }

  public editTalentSalary(body: EditModels.EditSalary, id: number): Observable<EditModels.EditResp> {
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_SALARY}/${id}`,
      body
    );
  }

  public editTalentSocialLinks(body: EditModels.EditSocialLinks, id: number): Observable<EditModels.EditResp> {
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_SOCIAL_LINKS}/${id}`,
      body
    );
  }

  public editEducationalExperience(body: EditModels.EditEducExp, idTalent: number, idEducExp: number): Observable<EditModels.EditResp> {
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_EDUC_EXP}/${idTalent}/${idEducExp}`,
      body
    );
  }

  public editWorkExperience(body: EditModels.EditWorkExp, idTalent: number, idWorkExp: number): Observable<EditModels.EditResp> {
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_WORK_EXP}/${idTalent}/${idWorkExp}`,
      body
    );
  }

  public editLanguageExpertise(body: EditModels.EditLang, idTalent: number, idLangExp: number): Observable<EditModels.EditResp> {
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_LANG_EXP}/${idTalent}/${idLangExp}`,
      body
    );
  }

}
