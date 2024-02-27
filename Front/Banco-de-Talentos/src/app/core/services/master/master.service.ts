import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

import * as MasterModels from 'src/app/shared/models/interfaces/master.interfaces';
import { UrlConstants } from '../../global/constants/url.constants';

@Injectable({
  providedIn: 'root',
})
export class MasterService {
  constructor(private httpClient: HttpClient) {}

  getLanguages(): Observable<MasterModels.LanguageModel[]> {
    return this.httpClient.get<MasterModels.LanguageModel[]>(
      UrlConstants.URL_REQ_LANG
    );
  }

  getRoles(): Observable<MasterModels.RoleModel[]> {
    return this.httpClient.get<MasterModels.RoleModel[]>(
      UrlConstants.URL_REQ_ROLES
    );
  }

  getCurrencies(): Observable<MasterModels.CurrenciesModel[]> {
    return this.httpClient.get<MasterModels.CurrenciesModel[]>(
      UrlConstants.URL_REQ_CURRS
    );
  }

  getProfiles(): Observable<MasterModels.ProfileModel[]> {
    return this.httpClient.get<MasterModels.ProfileModel[]>(
      UrlConstants.URL_REQ_PROF
    );
  }

  getLangProficiency(): Observable<MasterModels.LangProficiencyModel[]> {
    return this.httpClient.get<MasterModels.LangProficiencyModel[]>(
      UrlConstants.URL_REQ_LANG_PROF
    );
  }

  getCountries(): Observable<MasterModels.CountryModel[]> {
    return this.httpClient.get<MasterModels.CountryModel[]>(
      UrlConstants.URL_REQ_COUNTRIES
    );
  }

  getCities(id: number): Observable<MasterModels.CityModel[]> {
    return this.httpClient.get<MasterModels.CityModel[]>(
      UrlConstants.URL_REQ_CITIES_1 + id + UrlConstants.URL_REQ_CITIES_2
    );
  }
}
