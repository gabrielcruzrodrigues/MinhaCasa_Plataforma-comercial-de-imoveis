import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ImmobileService {
  constructor(private http: HttpClient, private authService: AuthService) {}

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
}