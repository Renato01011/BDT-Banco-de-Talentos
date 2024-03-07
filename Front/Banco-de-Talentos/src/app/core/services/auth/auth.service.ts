import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';

import { UrlConstants } from '../../global/constants/url.constants';
import { STORAGE_CURRENT_TOKEN } from '../../global/constants/constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient, public jwtHelper: JwtHelperService) { }

  loginUser(username: String, password: String): boolean {
    // -- Temporary Code -- //
    if (username == "Test" && password == "Test1") {
      sessionStorage.setItem(STORAGE_CURRENT_TOKEN, JSON.stringify("TestToken"));
      return true;
    }
    else {
      return false;
    }
    // -- Temporary Code -- //
  }

  public isAuthenticated(): boolean {
    if (sessionStorage.getItem(STORAGE_CURRENT_TOKEN) == null) { return false; }
    const token = JSON.parse(sessionStorage.getItem(STORAGE_CURRENT_TOKEN) || '{}');
    // return !this.jwtHelper.isTokenExpired(token);
    
    // -- Temporary Code -- //
    if (token == "TestToken") {
      return true;
    }
    else {
      return false;
    }
    // -- Temporary Code -- //
  }
}
