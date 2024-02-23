import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

import { NewTalentModel } from 'src/app/shared/models/interfaces/talent.interfaces';
import { UrlConstants } from '../../global/constants/url.constants';
import { PostResponse } from 'src/app/shared/models/interfaces/respone.interfaces';

@Injectable({
  providedIn: 'root'
})
export class TalentService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  constructor(private httpClient: HttpClient) { }

  postNewTalent(newtalent: NewTalentModel): Observable<PostResponse> {
    return this.httpClient.post<PostResponse>(UrlConstants.URL_POST_TALENT, newtalent, this.httpOptions);
  }

}
