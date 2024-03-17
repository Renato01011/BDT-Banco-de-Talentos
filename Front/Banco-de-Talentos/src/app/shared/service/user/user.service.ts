import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Picture } from '../../models/interfaces/user.interfaces';
import { UrlConstants } from 'src/app/core/global/constants/url.constants';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private httpClient: HttpClient) {}

  getPicture(username: string): Observable<Picture> {
    const body = { name: username };
    return this.httpClient.post<Picture>(UrlConstants.URL_REQ_USER, body);
  }
}
