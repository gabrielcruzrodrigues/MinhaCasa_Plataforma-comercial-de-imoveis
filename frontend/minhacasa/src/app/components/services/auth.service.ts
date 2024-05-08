import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  configureLocalStorage(body: any) {
    if (body) {
      localStorage.setItem('userId', body.id);
      localStorage.setItem('role', body.role)
      localStorage.setItem('token', body.token)
    } else {
      console.log("Erro ao configurar o localStorage.");
    }
  }
}
