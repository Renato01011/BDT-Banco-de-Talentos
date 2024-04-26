import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { FilterRequest } from 'src/app/shared/models/interfaces/filterReq.interfaces';
import { FilterResponse } from 'src/app/shared/models/interfaces/filterResp.interfaces';
import { UrlConstants } from '../../global/constants/url.constants';

@Injectable({
  providedIn: 'root',
})
export class FilterService {
  public resultMsg: string = '';
  public total: number = 0;

  constructor(private httpClient: HttpClient) { }

  filterTalent(filter: FilterRequest): Observable<FilterResponse[]> {
    return this.httpClient
      .post<FilterResponse[]>(UrlConstants.URL_REQ_FILTER, filter)
      .pipe(tap((resp) => this.generateResultMsg(resp, filter.nameJobTitle)));
  }

  private generateResultMsg(talents: FilterResponse[], filterName: string) {
    this.total = talents.length;
    const name = this.generateFilterMsg(filterName);
    if (this.total === 1) {
      this.resultMsg = `Un resultado disponibles para ${name}`;
    } else {
      this.resultMsg = ` ${this.total} resultados disponibles para ${name}`;
    }
  }

  private generateFilterMsg(name: string): string {
    return name.trim().length === 0 ? 'tu búsqueda' : `"${name}"`;
  }
}
