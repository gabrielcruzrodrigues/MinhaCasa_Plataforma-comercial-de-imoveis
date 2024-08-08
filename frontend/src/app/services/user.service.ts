import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  readonly url = environment.url + '/user'

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

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
  
  addFavoriteImmobile(immobileId: any): Observable<any> {
    const headers = this.authService.getHeaders();
    const userId = this.getIdOfTheUserLogged();
    const urlForRequest = this.url + `/favorite/add/${userId}/${immobileId}`;
    return this.http.put(urlForRequest, {}, { headers: headers, observe: 'response'});
  }

  removeFavoriteImmobile(immobileId: any): Observable<any> {
    const headers = this.authService.getHeaders();
    const userId = this.getIdOfTheUserLogged();
    const urlForRequest = this.url + `/favorite/remove/${userId}/${immobileId}`;
    return this.http.put(urlForRequest, {}, { headers: headers, observe: 'response'});
  }
}
