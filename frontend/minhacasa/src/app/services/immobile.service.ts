import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { enviroment } from '../../environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImmobileService {
  readonly url = enviroment.url + '/immobile'

  constructor(private http: HttpClient) { }

  create(data: any): Observable<any> {
    return this.http.post(this.url, data, {observe: 'response'});
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
}
