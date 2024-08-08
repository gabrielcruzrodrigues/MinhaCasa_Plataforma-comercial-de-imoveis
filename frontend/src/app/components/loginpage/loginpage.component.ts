import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ModalTextComponent } from '../layout/modal-text/modal-text.component';
import { LoadingComponent } from '../layout/loading/loading.component';
import { ModalAlertComponent } from '../layout/modal-alert/modal-alert.component';
import { ConverterFieldName } from '../../utils/ConverterFieldNameToPortuguese';

interface ResponseInterface {
  id: number,
  token: string,
  role: string
}

@Component({
  selector: 'app-loginpage',
  standalone: true,
  imports: [
    ReactiveFormsModule, FormsModule, ModalTextComponent, LoadingComponent, ModalAlertComponent
  ],
  templateUrl: './loginpage.component.html',
  styleUrl: './loginpage.component.scss'
})
export class LoginpageComponent {
  form: FormGroup;
  showModalText: boolean = false;
  message: string = '';
  @ViewChild(ModalTextComponent) modalComponent!: ModalTextComponent;
  isLoading: boolean = false;

  showModal: boolean = false;
  field: string = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private loginService: LoginService,
    private authService: AuthService,
    private cdr: ChangeDetectorRef,
  ) {
      this.form = this.fb.group({
        email: ['', Validators.required],
        password: ['', Validators.required]
      });
    }

    submit() {
      this.isLoading = true;
      const formData = this.form.value;

      this.loginService.login(formData).subscribe({
        next: (response: HttpResponse<ResponseInterface>) => {
          console.log(response);
          if (response.status == 200) {
            this.authService.configureLocalStorage(response.body);
            this.isLoading = false;
            this.activeModalText("Login efetuado com sucesso!");
            this.waitForModalClose().then(() => {
              this.router.navigate(["/"]);
            })
          }
        },
        error: (error) => {
          this.isLoading = false;
          if (error.status == 401) {
            this.activeModalText("Credenciais incorretas, tente novamente!");
          } else {
            this.activeModalText("Ocorreu um erro, tente novamente mais tarde!");
          }
        }
      })
    }

    verifyFields() {
      const invalidFields = this.getInvalidFields();
     
      if (invalidFields[0]) {
        this.showModal = false;
        setTimeout(() => {
          this.field = ConverterFieldName.verify(invalidFields[0]);
          this.showModal = true;
          this.cdr.detectChanges();
        });
      } else {
        this.submit();
      }
    }
  
    getInvalidFields(): string[] {
      const invalidFields: string[] = [];
  
      Object.keys(this.form.controls).forEach(key => {
        const control = this.form.get(key);
        if (control && control.invalid) {
          invalidFields.push(key);
        }
      });
  
      return invalidFields;
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
