import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';

import { TokenResponse } from '../..//../shared/models/interfaces/respone.interfaces';
import { UrlConstants } from '../../global/constants/url.constants';
import { STORAGE_CURRENT_TOKEN } from '../../global/constants/constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private httpClient: HttpClient, public jwtHelper: JwtHelperService) { }

  loginUser(username: String, password: String): Observable<TokenResponse> {
    return this.httpClient.post<TokenResponse>(
      UrlConstants.URL_LOGIN_TOKEN,
      {
        name: username,
        pwd: password
      },
      this.httpOptions
    );
  }

  public isAuthenticated(): boolean {
    if (sessionStorage.getItem(STORAGE_CURRENT_TOKEN) == null) { return false; }
    const token = JSON.parse(sessionStorage.getItem(STORAGE_CURRENT_TOKEN) || '{}');
    return !this.jwtHelper.isTokenExpired(token);
  }
}
