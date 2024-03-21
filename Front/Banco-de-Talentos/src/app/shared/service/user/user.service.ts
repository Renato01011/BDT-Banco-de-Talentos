import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Picture } from '../../models/interfaces/user.interfaces';
import { UrlConstants } from 'src/app/core/global/constants/url.constants';
import { PostResponse, NewUserListRespone } from '../../models/interfaces/respone.interfaces';
import { UserList } from '../../models/interfaces/userList.interfaces';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private httpClient: HttpClient) {}

  getPicture(username: string): Observable<Picture> {
    const body = { name: username };
    return this.httpClient.post<Picture>(UrlConstants.URL_REQ_USER, body);
  }

  addNewList(userId: number, listName: string): Observable<NewUserListRespone> {
    const body = { listName: listName }
    return this.httpClient.post<NewUserListRespone>(
      `${UrlConstants.URL_ADD_LIST_USER}/${userId}`, 
      body
    );
  }

  getUserLists(userId: number): Observable<UserList[]> {
    return this.httpClient.get<UserList[]>(
      `${UrlConstants.URL_GET_LISTS_USER}/${userId}`
    );
  }

  addTalentToList(idListUser: number, idTalent: number) {
    const body = { idTalent: idTalent }
    return this.httpClient.post<PostResponse>(
      `${UrlConstants.URL_ADD_LIST_USER}/${idListUser}`,
      body
    );
  }
}
