import { environment } from 'src/environments/environment';

export class UrlConstants {
  public static readonly URL_LOGIN_TOKEN = 
    environment.host + environment.context + '/auth/login';
  public static readonly URL_REQ_LANG =
    environment.host + environment.context + '/master/languages';
  public static readonly URL_REQ_ROLES =
    environment.host + environment.context + '/master/roles';
  public static readonly URL_REQ_CURRS =
    environment.host + environment.context + '/master/currencies';
  public static readonly URL_REQ_PROF =
    environment.host + environment.context + '/master/profiles';
  public static readonly URL_REQ_LANG_PROF =
    environment.host + environment.context + '/master/proficiency';
  public static readonly URL_REQ_COUNTRIES =
    environment.host + environment.context + '/master/countries';
  public static readonly URL_REQ_CITIES =
    environment.host + environment.context + '/master/cities';
}
