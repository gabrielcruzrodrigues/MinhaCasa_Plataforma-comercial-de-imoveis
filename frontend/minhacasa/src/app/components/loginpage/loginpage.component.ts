import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginService } from '../services/login.service';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

interface ResponseInterface {
  id: number,
  token: string,
  role: string
}

@Component({
  selector: 'app-loginpage',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './loginpage.component.html',
  styleUrl: './loginpage.component.scss'
})
export class LoginpageComponent {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private loginService: LoginService,
    private authService: AuthService
  ) {
      this.form = this.fb.group({
        email: [''],
        password: ['']
      });
    }

    submit() {
      const formData = this.form.value;
      console.log(formData);
      this.loginService.login(formData).subscribe({
        next: (response: HttpResponse<ResponseInterface>) => {
          console.log(response);
          if (response.status == 200) {
            this.authService.configureLocalStorage(response.body);
            this.router.navigate(["/"])
          }
        },
        error: (error) => {
          if (error.status == 401) {
            alert("login não foi efetuado com sucesso!");
          }
        }
      })
    }
}
