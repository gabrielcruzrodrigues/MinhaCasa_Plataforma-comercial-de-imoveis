import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class ImmobileService {
  constructor(
    private http: HttpClient, 
    private authService: AuthService,
    private userService: UserService  
  ) {}

  readonly url = environment.url + '/immobile';
  
  create(data: any): Observable<any> {
    const headers = this.authService.getHeaders();
    return this.http.post(this.url, data, {headers, observe: 'response'});
  }

  getImmobileWithCompleteImagesPath(id: any): Observable<any> {
    let urlForRequest = this.url + "/details/" + id;
    return this.http.get(urlForRequest, {observe: 'response'});
  }

  search(params: any): Observable<any> {
    let urlForRequest = this.url + "/search";
    return this.http.post(urlForRequest, params, {observe: 'response'});
  }

  soldImmobile(id: string): Observable<any> {
    let urlForRequest = this.url + `/sold/${id}`;
    return this.http.put(urlForRequest, {}, {observe: 'response', responseType: 'text'});
  }

  find4immobilesForHome(): Observable<any> {
    let urlForRequest = this.url + '/cards';
    return this.http.get(urlForRequest, {observe: 'response'});
  }

  searchForUserFavoriteImmobiles(): Observable<any> {
    const userId = this.userService.getIdOfTheUserLogged();
    const headers = this.authService.getHeaders();
    const urlForRequest = this.url + `/favorites/${userId}`;
    return this.http.get(urlForRequest, {headers: headers, observe: 'response'});
  }

  searchIdsOfImmobilesFavorited() {
    const userId = this.userService.getIdOfTheUserLogged();
    const headers = this.authService.getHeaders();
    const urlForRequest = this.url + `/favorites/user/${userId}`;
    return this.http.get(urlForRequest, {headers: headers, observe: 'response'});
  }

  findById(immobileId: any): Observable<any> {
    const headers = this.authService.getHeaders();
    const urlForRequest = this.url + `/${immobileId}`;
    return this.http.get(urlForRequest, {headers: headers, observe: 'response'});
  }
}
