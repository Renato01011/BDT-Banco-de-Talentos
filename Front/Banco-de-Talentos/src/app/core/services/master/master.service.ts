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

  getGeneralData(): Observable<MasterModels.General> {
    return this.httpClient.post<MasterModels.General>(
      UrlConstants.URL_REQ_GENERAL,
      null
    ).pipe(
      tap((general) => {
        this.cacheStorage.byLanguage = { languages: general.languages };
        this.cacheStorage.byCurrency = { currencies: general.currencies };
        this.cacheStorage.byLangProficiency = { proficiencies: general.proficiency };
        this.cacheStorage.byCountry = { countries: general.countries };
        this.cacheStorage.byTechSkill = { techSkills: general.skills };
      }),
      tap(() => this.sveToSessionStorage()
      ));
  }

  // getLanguages(): Observable<MasterModels.LanguageModel[]> {
  //   return this.httpClient
  //     .post<MasterModels.LanguageModel[]>(UrlConstants.URL_REQ_LANG, null)
  //     .pipe(
  //       tap((languages) => (this.cacheStorage.byLanguage = { languages })),
  //       tap(() => this.sveToSessionStorage())
  //     );
  // }

  // getRoles(): Observable<MasterModels.RoleModel[]> {
  //   return this.httpClient.post<MasterModels.RoleModel[]>(
  //     UrlConstants.URL_REQ_ROLES,
  //     null
  //   );
  // }

  // getCurrencies(): Observable<MasterModels.CurrenciesModel[]> {
  //   return this.httpClient
  //     .post<MasterModels.CurrenciesModel[]>(UrlConstants.URL_REQ_CURRS, null)
  //     .pipe(
  //       tap((currencies) => (this.cacheStorage.byCurrency = { currencies })),
  //       tap(() => this.sveToSessionStorage())
  //     );
  // }

  // getProfiles(): Observable<MasterModels.ProfileModel[]> {
  //   return this.httpClient
  //     .post<MasterModels.ProfileModel[]>(UrlConstants.URL_REQ_PROF, null)
  //     .pipe(
  //       tap((profiles) => (this.cacheStorage.byProfile = { profiles })),
  //       tap(() => this.sveToSessionStorage())
  //     );
  // }

  getLangProficiency(): Observable<MasterModels.LangProficiencyModel[]> {
    return this.httpClient
      .post<MasterModels.LangProficiencyModel[]>(
        UrlConstants.URL_REQ_LANG_PROF,
        null
      )
      .pipe(
        tap(
          (proficiencies) =>
            (this.cacheStorage.byLangProficiency = { proficiencies })
        ),
        tap(() => this.sveToSessionStorage())
      );
  }

  // getCountries(): Observable<MasterModels.CountryModel[]> {
  //   return this.httpClient
  //     .post<MasterModels.CountryModel[]>(UrlConstants.URL_REQ_COUNTRIES, null)
  //     .pipe(
  //       tap((countries) => (this.cacheStorage.byCountry = { countries })),
  //       tap(() => this.sveToSessionStorage())
  //     );
  // }

  getCities(id: number): Observable<MasterModels.CityModel[]> {
    const body = { idCountry: id };
    return this.httpClient.post<MasterModels.CityModel[]>(
      UrlConstants.URL_REQ_CITIES,
      body
    );
  }

  getTechSkills(): Observable<TechSkills[]> {
    return this.httpClient
      .post<TechSkills[]>(UrlConstants.URL_REQ_TEC_SKILL, null)
      .pipe(
        tap((techSkills) => (this.cacheStorage.byTechSkill = { techSkills })),
        tap(() => this.sveToSessionStorage())
      );
  }
}
