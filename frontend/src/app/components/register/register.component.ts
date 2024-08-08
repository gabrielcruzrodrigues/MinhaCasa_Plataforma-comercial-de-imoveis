import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { ModalTextComponent } from '../layout/modal-text/modal-text.component';
import { ModalAlertComponent } from '../layout/modal-alert/modal-alert.component';
import { ConverterFieldName } from '../../utils/ConverterFieldNameToPortuguese';
import { HttpResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { LoadingComponent } from '../layout/loading/loading.component';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule, ModalTextComponent, ModalAlertComponent, LoadingComponent
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  form: FormGroup;

  showModalText: boolean = false;
  message:string = '';

  showModal: boolean = false;
  field:string = '';

  isLoading: boolean = false;

  @ViewChild(ModalTextComponent) modalComponent!: ModalTextComponent;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private userService: UserService,
    private cdr: ChangeDetectorRef,
    private authService: AuthService
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      email: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      phone: ['', Validators.required],
      whatsapp: ['', Validators.required],
      password: ['', Validators.required],
      passwordVerify: ['', Validators.required]
    })
  }

  submit():void {
    this.verifyPassword();
    this.isLoading = true;

    this.userService.registerUser(this.form.value).subscribe({
      next: (response: HttpResponse<any>) => {
        this.authService.configureLocalStorage(response.body);
        this.isLoading = false;
        this.activeModalText('Cadastro realizado com sucesso!');
        this.waitForModalClose().then(() => {
          this.router.navigate(["/"]);
        })
      },
      error: () => {
        this.isLoading = false;
        this.activeModalText('Ocorreu um erro interno, por favor tente mais tarde!');
      }
    })
  }

  verifyFields() {
    const whatsapp = this.form.get('phone')?.value;
    this.form.patchValue({
      whatsapp: whatsapp
    })

    const invalidFields = this.getInvalidFields();
    
    if (invalidFields[0]) {
      this.showModal = false;
      setTimeout(() => {
        this.field = ConverterFieldName.verify(invalidFields[0]);
        this.showModal = true;
        this.cdr.detectChanges();
      });
    } else {
      if (this.verifyPassword()) {
        this.submit();
      }
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

  verifyPassword(): boolean {
    const verifyPassword = this.form.get('passwordVerify')?.value;
    const password = this.form.get('password')?.value;

    if (verifyPassword != password) {
      this.activeModalText('As senhas não são iguais!');
      return false;
    } 
    
    if (password.length < 8) {
      this.activeModalText('Sua senha deve ter mais de 8 caracters!');
      return false;
    } 

    if (!this.validatePassword(password)) {
      this.activeModalText('Sua senha deve conter letras, numeros e caracteres especiais (#, %, &, *, @, !...)!');
      return false;
    }
    
    return true;
  }

  validatePassword(password: string) {
    const hasLetters = /[a-zA-Z]/.test(password);
    const hasNumbers = /\d/.test(password);      
    const hasSpecialChars = /[!@#$%^&*(),.?":{}|<>]/.test(password);

    if (hasLetters && hasNumbers && hasSpecialChars) {
      return true;
    } else {
      return false;
    }
  }

  verifyIfPasswordNotNull(): boolean {
    if (!this.form.get('password')?.valid) {
      this.activeModalText("Crie uma senha antes de verificar!");
      return false;
    }
    return true;
  }
}
