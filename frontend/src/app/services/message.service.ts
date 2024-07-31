import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  readonly url = environment.url + '/message';

  constructor(private http: HttpClient, private authService: AuthService) { }

  createMessage(data: any): Observable<any> {
    const headers = this.authService.getHeaders();
    return this.http.post(this.url, data, {headers, observe: 'response'});
  }
}
