import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import * as EditModels from '../../models/interfaces/editinfo.interfaces';
import { UrlConstants } from 'src/app/core/global/constants/url.constants';
import { AddFile } from '../../models/interfaces/addInfo.interfaces';

@Injectable({
  providedIn: 'root',
})
export class EditInfoService {
  constructor(private httpClient: HttpClient) {}

  public editTalentDescription(
    body: EditModels.EditDescription,
    id: number
  ): Observable<EditModels.EditResp> {
    const sendBody = {
      id: id,
      ...body,
    };
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_DESCRIPTION}`,
      sendBody
    );
  }

  public editTalentProfilePicture(
    body: EditModels.EditProfilePicture,
    id: number
  ): Observable<EditModels.EditResp> {
    const sendBody = {
      id: id,
      ...body,
    };
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_PROFILE_PICTURE}`,
      sendBody
    );
  }

  public editTalentSalary(
    body: EditModels.EditSalary,
    id: number
  ): Observable<EditModels.EditResp> {
    const sendBody = {
      id: id,
      ...body,
    };
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_SALARY}`,
      sendBody
    );
  }

  public editTalentSocialLinks(
    body: EditModels.EditSocialLinks,
    id: number
  ): Observable<EditModels.EditResp> {
    const sendBody = {
      id: id,
      ...body,
    };
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_SOCIAL_LINKS}`,
      sendBody
    );
  }

  public editEducationalExperience(
    body: EditModels.EditEducExp,
    idTalent: number,
    idEducExp: number
  ): Observable<EditModels.EditResp> {
    const sendBody = {
      idEducExp: idEducExp,
      idTalent: idTalent,
      ...body
    };
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_EDUC_EXP}`,
      sendBody
    );
  }

  public editWorkExperience(
    body: EditModels.EditWorkExp,
    idTalent: number,
    idWorkExp: number
  ): Observable<EditModels.EditResp> {
    const sendBody = {
      idWorkExp: idWorkExp,
      idTalent: idTalent,
      ...body,
    };
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_WORK_EXP}`,
      sendBody
    );
  }

  public editLanguageExpertise(
    body: EditModels.EditLang,
    idTalent: number,
    idLangExp: number
  ): Observable<EditModels.EditResp> {
    const sendBody = {
      idTalentLang: idLangExp,
      idTalent: idTalent,
      ...body,
    };
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_EDIT_LANG_EXP}`,
      sendBody
    );
  }

  public updateResume(
    body: AddFile,
    idFile: number,
    id: number
  ): Observable<EditModels.EditResp> {
    const sendBody = {
      idFile: idFile,
      idTalent: id,
      ...body,
    };
    return this.httpClient.put<EditModels.EditResp>(
      `${UrlConstants.URL_UPDATE_CV}`,
      sendBody
    );
  }
}
