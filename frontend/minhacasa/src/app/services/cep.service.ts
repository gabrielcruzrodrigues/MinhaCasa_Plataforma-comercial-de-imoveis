import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CepService {

  constructor(private http: HttpClient) { }

  findCities(state: any): Observable<any> {
    const url = `https://servicodados.ibge.gov.br/api/v1/localidades/estados/${state}/distritos`;
    return this.http.get(url, {observe: 'response'});
  } 
}
