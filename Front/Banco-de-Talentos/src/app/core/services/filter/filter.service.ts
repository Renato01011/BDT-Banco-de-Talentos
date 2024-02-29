import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FilterRequest } from 'src/app/shared/models/interfaces/filterReq.interfaces';
import { FilterResponse } from 'src/app/shared/models/interfaces/filterResp.interfaces';
import { UrlConstants } from '../../global/constants/url.constants';

@Injectable({
  providedIn: 'root',
})
export class FilterService {
  constructor(private httpClient: HttpClient) {}

  filterTalent(filter: FilterRequest): Observable<FilterResponse[]> {
    return this.httpClient.post<FilterResponse[]>(
      UrlConstants.URL_REQ_FILTER,
      filter
    );
  }
}
