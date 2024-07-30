import { Component, ChangeDetectorRef, ViewChild, NgModule } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { HttpResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { ModalAlertComponent } from '../layout/modal-alert/modal-alert.component';
import { CommonModule } from '@angular/common';
import { ModalTextComponent } from '../layout/modal-text/modal-text.component';
import { CepService } from '../../services/cep.service';
import { LoadingComponent } from '../layout/loading/loading.component';
import { ConverterFieldName } from '../../utils/ConverterFieldNameToPortuguese';

interface cepInterface {
  nome: string
}

@Component({
  selector: 'app-registerpage',
  standalone: true,
  imports: [
    ReactiveFormsModule, FormsModule, ModalAlertComponent, CommonModule, ModalTextComponent, LoadingComponent
  ],
  templateUrl: './registerpage.component.html',
  styleUrl: './registerpage.component.scss'
})
export class RegisterpageComponent {
  form: FormGroup;
  formData = new FormData();
  cities: string[] = [];
  currentImageUrl: string | null = null;

  //modal alert
  showModal: boolean = false;
  field: string = '';

  //modal text
  showModalText: boolean = false;
  message: string = '';
  @ViewChild(ModalTextComponent) modalComponent!: ModalTextComponent;
  isLoading: boolean = false;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private cdr: ChangeDetectorRef,
    private cepService: CepService
  ) {
    this.form = this.fb.group({
      image: [null, Validators.required],
      name: ['', Validators.required],
      email: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      state: ['', Validators.required],
      phone: ['', Validators.required],
      whatsapp: ['', Validators.required],
      city: ['', Validators.required],
      gender: ['', Validators.required],
      password: ['', Validators.required],
      passwordVerify: ['', Validators.required]
    });
  }

  submit() {
    this.isLoading = true;
    this.populateFormData();
    this.userService.registerUser(this.formData).subscribe({
      next: (response: HttpResponse<any>) => {
        this.authService.configureLocalStorage(response.body);
        this.isLoading = false;
        this.activeModalText('Cadastro realizado com sucesso!');
        this.waitForModalClose().then(() => {
          this.router.navigate(["/"]);
        })
      },
      error: (error) => {
        this.isLoading = false;
        this.activeModalText('Ocorreu um erro interno, por favor tente mais tarde!');
        this.waitForModalClose().then(() => {
          this.router.navigate(["/"]);
        })
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

  populateFormData():void {
    this.formData.set('name', this.form.get('name')?.value);
    this.formData.set('email', this.form.get('email')?.value);
    this.formData.set('state', this.form.get('state')?.value);
    this.formData.set('phone', this.form.get('phone')?.value);
    this.formData.set('whatsapp', this.form.get('whatsapp')?.value);
    this.formData.set('dateOfBirth', this.form.get('dateOfBirth')?.value);
    this.formData.set('city', this.form.get('city')?.value);
    this.formData.set('gender', this.form.get('gender')?.value);
    this.formData.set('password', this.form.get('password')?.value);
  }

  verifyPassword(): boolean {
    const firstPassword = this.form.get('passwordVerify')?.value;
    const password = this.form.get('password')?.value;

    if (firstPassword != password) {
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

  activeModalText(text: string):void {
    this.showModalText = false; //reset
    setTimeout(() => {
      this.message = text;
      this.showModalText = true;
      this.cdr.detectChanges();
    });
  }

  onFileSelect(event: Event): void {
    const element = event.currentTarget as HTMLInputElement;
    const file: File | null = element.files ? element.files[0] : null;
    
    if (file && this.isImageFile(file)) {
        this.formData.append('imageProfile', file);

        const reader = new FileReader();
        reader.onload = (e: any) => {
            const imageUrl = e.target.result;
            const imageProfileDiv = document.querySelector('.image-profile-div') as HTMLElement;
            if (imageProfileDiv) {
                if (this.currentImageUrl) {
                    URL.revokeObjectURL(this.currentImageUrl);
                }
                imageProfileDiv.style.backgroundImage = `url(${imageUrl})`;
                imageProfileDiv.style.backgroundSize = 'cover';
                imageProfileDiv.style.backgroundPosition = 'center';
                imageProfileDiv.style.backgroundRepeat = 'no-repeat';
                this.currentImageUrl = imageUrl;
            }
        };
        reader.readAsDataURL(file);

        element.value = '';
    } else {
      this.activeModalText("Apenas imagens são permitidas, tente novamente!");
    }
  }

  isImageFile(file: File): boolean {
    const imageTypes = ['image/jpeg', 'image/png', 'image/gif', 'image/bmp'];
    return imageTypes.includes(file.type);
  }

  waitForModalClose(): Promise<void> {
    return new Promise(resolve => {
      this.modalComponent.onClose.subscribe(() => resolve());
    })
  }

  findCitiesByState(event: Event) {
    const selectedState = this.form.get('state')?.value;
    this.cities = [];
    
    this.cepService.findCities(selectedState).subscribe({
      next: (response: HttpResponse<any>) => {
        if (response.body && Array.isArray(response.body)) {
          this.cities = response.body.map((city: cepInterface) => city.nome)
            .sort((a, b) => a.localeCompare(b));
        }
      },
      error: (error) => {
        this.activeModalText("Aconteceu um erro interno, Por favor tente mais tarde.");
        this.waitForModalClose().then(() => {
          this.router.navigate(["/"]);
        })
        console.log('Erro ao buscar cidades por UF: ', error);
      }
    });
  }

  searchCity(event: Event) {
    if (!this.form.get('state')?.valid) {
      this.activeModalText("Selecione o seu estado para continuar!");
      this.form.patchValue({
        cidade: ''
      })
    }
  }

  verifyIfPasswordNotNull(): void {
    if (!this.form.get('password')?.valid) {
      this.activeModalText("Crie uma senha antes de verificar!");
      this.form.patchValue({
        verificação_de_senha: ''
      })
    }
  }
}
