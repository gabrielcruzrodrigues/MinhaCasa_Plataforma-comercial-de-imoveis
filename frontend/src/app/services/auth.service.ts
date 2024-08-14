import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  url: string = environment.url;

  isLogged():Observable<any> {
    const headers = this.getHeaders();
    const urlForRequest = this.url + "/user/isLogged";
    return this.http.get(urlForRequest, {headers: headers, observe: 'response'});
  }

  configureLocalStorage(body: any): void {
    if (body) {
      localStorage.setItem('userId', body.id);
      localStorage.setItem('role', body.role)
      localStorage.setItem('token', body.token)
    } else {
      console.log("Erro ao configurar o localStorage.");
    }
  }

  clearLocalStorage(): void {
    if (typeof window !== 'undefined' && typeof window.localStorage !== 'undefined') {
      localStorage.clear();
    } else {
      return;
    }
  } 

  getUserId() {
    if (typeof window !== 'undefined' && typeof window.localStorage !== 'undefined') {
      return localStorage.getItem('userId');
    } else {
      return;
    }
  }

  verifyIfAreLoggedIn() {
    if (typeof window !== 'undefined' && typeof window.localStorage !== 'undefined') {
      const token = localStorage.getItem('token');
      return token ? true : false;
    } else {
      return;
    }
  }

  logout(): void {
    if (typeof window !== 'undefined' && typeof window.localStorage !== 'undefined') {
      localStorage.removeItem('userId');
      localStorage.removeItem('role');
      localStorage.removeItem('token');
    } 
  }

  getHeaders() {
    if (typeof window !== 'undefined' && typeof window.localStorage !== 'undefined') {
      const token = localStorage.getItem('token');
      if (token) {
        return new HttpHeaders().set('Authorization', `Bearer ${token}`);
      } else {
        return new HttpHeaders();
      }
    } else {
      return;
    }
  }
}
