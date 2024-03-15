import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

import * as MasterModels from 'src/app/shared/models/interfaces/master.interfaces';
import { UrlConstants } from '../../global/constants/url.constants';
import { CacheStorage } from 'src/app/shared/models/interfaces/cacheStorage.interfaces';
import { TechSkills } from 'src/app/shared/models/interfaces/techSkill.interfaces';
import { MASTER_STORAGE } from '../../global/constants/constants';

@Injectable({
  providedIn: 'root',
})
export class MasterService {
  public cacheStorage: CacheStorage = {
    byTechSkill: { techSkills: [] },
    byLanguage: { languages: [] },
    byCurrency: { currencies: [] },
    byProfile: { profiles: [] },
    byLangProficiency: { proficiencies: [] },
    byCountry: { countries: [] },
  };

  constructor(private httpClient: HttpClient) {
    this.loadFromSessionStorage();
  }

  private sveToSessionStorage() {
    sessionStorage.setItem(MASTER_STORAGE, JSON.stringify(this.cacheStorage));
  }

  private loadFromSessionStorage() {
    if (!sessionStorage.getItem(MASTER_STORAGE)) return;
    this.cacheStorage = JSON.parse(sessionStorage.getItem(MASTER_STORAGE)!);
  }

  getLanguages(): Observable<MasterModels.LanguageModel[]> {
    return this.httpClient
      .get<MasterModels.LanguageModel[]>(UrlConstants.URL_REQ_LANG)
      .pipe(
        tap((languages) => (this.cacheStorage.byLanguage = { languages })),
        tap(() => this.sveToSessionStorage())
      );
  }

  getRoles(): Observable<MasterModels.RoleModel[]> {
    return this.httpClient.get<MasterModels.RoleModel[]>(
      UrlConstants.URL_REQ_ROLES
    );
  }

  getCurrencies(): Observable<MasterModels.CurrenciesModel[]> {
    return this.httpClient
      .get<MasterModels.CurrenciesModel[]>(UrlConstants.URL_REQ_CURRS)
      .pipe(
        tap((currencies) => (this.cacheStorage.byCurrency = { currencies })),
        tap(() => this.sveToSessionStorage())
      );
  }

  getProfiles(): Observable<MasterModels.ProfileModel[]> {
    return this.httpClient
      .get<MasterModels.ProfileModel[]>(UrlConstants.URL_REQ_PROF)
      .pipe(
        tap((profiles) => (this.cacheStorage.byProfile = { profiles })),
        tap(() => this.sveToSessionStorage())
      );
  }

  getLangProficiency(): Observable<MasterModels.LangProficiencyModel[]> {
    return this.httpClient
      .get<MasterModels.LangProficiencyModel[]>(UrlConstants.URL_REQ_LANG_PROF)
      .pipe(
        tap(
          (proficiencies) =>
            (this.cacheStorage.byLangProficiency = { proficiencies })
        ),
        tap(() => this.sveToSessionStorage())
      );
  }

  getCountries(): Observable<MasterModels.CountryModel[]> {
    return this.httpClient
      .get<MasterModels.CountryModel[]>(UrlConstants.URL_REQ_COUNTRIES)
      .pipe(
        tap((countries) => (this.cacheStorage.byCountry = { countries })),
        tap(() => this.sveToSessionStorage())
      );
  }

  getCities(id: number): Observable<MasterModels.CityModel[]> {
    return this.httpClient.get<MasterModels.CityModel[]>(
      UrlConstants.URL_REQ_CITIES_1 + id + UrlConstants.URL_REQ_CITIES_2
    );
  }

  getTechSkills(): Observable<TechSkills[]> {
    return this.httpClient
      .get<TechSkills[]>(UrlConstants.URL_REQ_TEC_SKILL)
      .pipe(
        tap((techSkills) => (this.cacheStorage.byTechSkill = { techSkills })),
        tap(() => this.sveToSessionStorage())
      );
  }
}
