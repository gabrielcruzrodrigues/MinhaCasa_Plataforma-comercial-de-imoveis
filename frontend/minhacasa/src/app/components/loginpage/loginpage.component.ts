import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ModalTextComponent } from '../layout/modal-text/modal-text.component';

interface ResponseInterface {
  id: number,
  token: string,
  role: string
}

@Component({
  selector: 'app-loginpage',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, ModalTextComponent],
  templateUrl: './loginpage.component.html',
  styleUrl: './loginpage.component.scss'
})
export class LoginpageComponent {
  form: FormGroup;
  showModalText: boolean = false;
  message: string = '';
  @ViewChild(ModalTextComponent) modalComponent!: ModalTextComponent;


  constructor(
    private fb: FormBuilder,
    private router: Router,
    private loginService: LoginService,
    private authService: AuthService,
    private cdr: ChangeDetectorRef,
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
            this.activeModalText("Login efetuado com sucesso!");
            this.waitForModalClose().then(() => {
              this.router.navigate(["/"]);
            })
          }
        },
        error: (error) => {
          if (error.status == 401) {
            this.activeModalText("Credenciais incorretas, tente novamente!");
          } else {
            this.activeModalText("Ocorreu um erro, tente novamente mais tarde!");
            this.waitForModalClose().then(() => {
              this.router.navigate(["/"]);
            })
          }
        }
      })
    }

    activeModalText(text: string):void {
      this.showModalText = false; //reset
      setTimeout(() => {
        this.message = text;
        this.showModalText = true;
        this.cdr.detectChanges();
      });
    }

    waitForModalClose(): Promise<void> {
      return new Promise(resolve => {
        this.modalComponent.onClose.subscribe(() => resolve());
      })
    }
}
