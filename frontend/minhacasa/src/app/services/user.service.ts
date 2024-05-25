import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { enviroment } from '../../environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  readonly url = enviroment.url + '/user'

  constructor(private http: HttpClient) { }

  registerUser(data: any): Observable<any> {
    return this.http.post(this.url, data, {observe: 'response'});
  }

  getIdOfTheUserLogged() {
    if (typeof window !== 'undefined' && typeof window.localStorage !== 'undefined') {
      return localStorage.getItem('userId');
    } else {
      return;
    }
  }

  findById(id: any): Observable<any> {
    const urlForRequest = this.url + `/${id}`; 
    return this.http.get(urlForRequest, {observe: 'response'});
  }

  findByIdForProfile(id: any): Observable<any> {
    const urlForRequest = this.url + `/profile/${id}`; 
    return this.http.get(urlForRequest, {observe: 'response'});
  }
}
