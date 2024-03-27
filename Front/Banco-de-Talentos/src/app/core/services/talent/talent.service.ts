import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { NewTalentModel } from 'src/app/shared/models/interfaces/talent.interfaces';
import { UrlConstants } from '../../global/constants/url.constants';
import { PostResponse } from 'src/app/shared/models/interfaces/respone.interfaces';
import { TalentResponse } from 'src/app/shared/models/interfaces/talentResp.interfaces';

@Injectable({
  providedIn: 'root',
})
export class TalentService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private httpClient: HttpClient) {}

  postNewTalent(newtalent: NewTalentModel): Observable<PostResponse> {
    return this.httpClient.post<PostResponse>(
      UrlConstants.URL_POST_TALENT,
      newtalent,
      this.httpOptions
    );
  }

  getTalentById(id: number, userId: number): Observable<TalentResponse> {
    const body = { idTalent: id, userId: userId };
    return this.httpClient.post<TalentResponse>(
      `${UrlConstants.URL_REQ_SRCH_BY_ID}`,
      body
    );
  }
}
