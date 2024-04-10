import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';

import { TokenResponse } from 'src/app/shared/models/interfaces/respone.interfaces';
import { UrlConstants } from '../../global/constants/url.constants';
import { STORAGE_CURRENT_TOKEN } from '../../global/constants/constants';
import { Observable } from 'rxjs';
import {
  Payload,
  Role,
} from 'src/app/shared/models/interfaces/payload.interfaces';
import { Authority } from 'src/app/shared/models/enums/authority.enum';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(
    private httpClient: HttpClient,
    public jwtHelper: JwtHelperService
  ) {}

  loginUser(username: string, password: string): Observable<TokenResponse> {
    return this.httpClient.post<TokenResponse>(
      UrlConstants.URL_LOGIN_TOKEN,
      {
        name: username,
        pwd: password,
      },
      this.httpOptions
    );
  }

  public isAuthenticated(): boolean {
    if (sessionStorage.getItem(STORAGE_CURRENT_TOKEN) == null) {
      return false;
    }
    const token = JSON.parse(
      sessionStorage.getItem(STORAGE_CURRENT_TOKEN) || '{}'
    );
    return !this.jwtHelper.isTokenExpired(token);
  }

  private decodeToken(): Payload | null {
    try {
      return this.jwtHelper.decodeToken(this.getTokenFromStorage());
    } catch (error) {
      console.error('Decoding has failed');
      return null;
    }
  }

  private getTokenFromStorage(): string {
    const token = sessionStorage.getItem(STORAGE_CURRENT_TOKEN);
    if (!token) throw new Error('Token is not found');
    return token;
  }

  public get payload(): any {
    return this.decodeToken();
  }

  public get username(): string {
    const username = this.decodeToken()?.sub ?? '';
    return username;
  }

  public get name(): string {
    const name = this.decodeToken()?.name ?? '';
    return name;
  }

  private getAuthority(roles: Role[]): boolean {
    return roles.some((role) => role.authority === Authority.RECLUTADOR);
  }

  // public get isRecruiter(): boolean {
  //   let tempRoles = this.decodeToken()?.roles.toString().split(',') ?? [];
  //   const roles = tempRoles.map((role) => { return { authority: Authority[role as keyof typeof Authority] } });
  //   return this.getAuthority(roles);
  // }

  public get isRecruiter(): boolean {
    const roles = this.decodeToken()?.roles ?? [];
    return this.getAuthority(roles);
  }

  public get idUser(): number {
    const id = this.decodeToken()?.id ?? 0;
    return id;
  }
}
