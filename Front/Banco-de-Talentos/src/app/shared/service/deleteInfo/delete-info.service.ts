import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import * as DeleteModels from '../../models/interfaces/deleteInfo.interfaces';
import { UrlConstants } from 'src/app/core/global/constants/url.constants';

@Injectable({
  providedIn: 'root'
})
export class DeleteInfoService {

  constructor(private httpClient: HttpClient) { }

  public deleteEducationalExperience(idTalent: number, idEducExp: number): Observable<DeleteModels.DeleteResp> {
    return this.httpClient.delete<DeleteModels.DeleteResp>(
      `${UrlConstants.URL_DELETE_EDUC_EXP}/${idTalent}/${idEducExp}`
    );
  }

  public deleteWorkExperience(idTalent: number, idWorkExp: number): Observable<DeleteModels.DeleteResp> {
    return this.httpClient.delete<DeleteModels.DeleteResp>(
      `${UrlConstants.URL_DELETE_WORK_EXP}/${idTalent}/${idWorkExp}`
    );
  }

  public deleteLanguageExpertise(idTalent: number, idLangExp: number): Observable<DeleteModels.DeleteResp> {
    return this.httpClient.delete<DeleteModels.DeleteResp>(
      `${UrlConstants.URL_DELETE_LANG_EXP}/${idTalent}/${idLangExp}`
    );
  }

}
