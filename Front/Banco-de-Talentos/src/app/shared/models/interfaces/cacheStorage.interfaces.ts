import {
  CountryModel,
  CurrenciesModel,
  LangProficiencyModel,
  LanguageModel,
  ProfileModel,
} from './master.interfaces';
import { TechSkills } from './techSkill.interfaces';

export interface CacheStorage {
  byTechSkill: ByTechSkill;
  byLanguage: ByLanguage;
  byCurrency: ByCurrency;
  byProfile: ByProfile;
  byLangProficiency: ByLangProficiency;
  byCountry: ByCountry;
}

export interface ByTechSkill {
  techSkills: TechSkills[];
}

export interface ByLanguage {
  languages: LanguageModel[];
}
export interface ByCurrency {
  currencies: CurrenciesModel[];
}
export interface ByProfile {
  profiles: ProfileModel[];
}
export interface ByLangProficiency {
  proficiencies: LangProficiencyModel[];
}
export interface ByCountry {
  countries: CountryModel[];
}
