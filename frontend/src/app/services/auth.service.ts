import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  configureLocalStorage(body: any): void {
    if (body) {
      localStorage.setItem('userId', body.id);
      localStorage.setItem('role', body.role)
      localStorage.setItem('token', body.token)
    } else {
      console.log("Erro ao configurar o localStorage.");
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
    const token = localStorage.getItem('token');
    if (token) {
      return new HttpHeaders().set('Authorization', `Bearer ${token}`);
    } else {
      return new HttpHeaders();
    }
  }
}
