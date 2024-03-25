import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  AddEducExp,
  AddFile,
  AddLang,
  AddResp,
  AddSoftSkill,
  AddTechSkill,
  AddWorkExp,
  AddFeedback,
} from '../../models/interfaces/addInfo.interfaces';
import { UrlConstants } from 'src/app/core/global/constants/url.constants';

@Injectable({
  providedIn: 'root',
})
export class AddInfoService {
  constructor(private httpClient: HttpClient) {}

  public addTechSkill(body: AddTechSkill, id: number): Observable<AddResp> {
    return this.httpClient.post<AddResp>(
      `${UrlConstants.URL_ADD_TEC_SKILL}/${id}`,
      body
    );
  }
  public addSoftSkill(body: AddSoftSkill, id: number): Observable<AddResp> {
    return this.httpClient.post<AddResp>(
      `${UrlConstants.URL_ADD_SFT_SKILL}/${id}`,
      body
    );
  }
  public addWorkExp(body: AddWorkExp, id: number): Observable<AddResp> {
    return this.httpClient.post<AddResp>(
      `${UrlConstants.URL_ADD_WRK_EXP}/${id}`,
      body
    );
  }
  public addEducExp(body: AddEducExp, id: number): Observable<AddResp> {
    return this.httpClient.post<AddResp>(
      `${UrlConstants.URL_ADD_EDUC}/${id}`,
      body
    );
  }
  public addLang(body: AddLang, id: number): Observable<AddResp> {
    return this.httpClient.post<AddResp>(
      `${UrlConstants.URL_ADD_LANG}/${id}`,
      body
    );
  }
  public addFile(body: AddFile, id: number): Observable<AddResp> {
    return this.httpClient.post<AddResp>(
      `${UrlConstants.URL_ADD_FILE}/${id}`,
      body
    );
  }
  public addFeedback(body: AddFeedback, id: number): Observable<AddResp> {
    return this.httpClient.post<AddResp>(
      `${UrlConstants.URL_ADD_FEEDBACK}/${id}`,
      body
    );
  }
}
