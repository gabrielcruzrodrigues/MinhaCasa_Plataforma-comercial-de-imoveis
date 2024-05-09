import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { HttpResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registerpage',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './registerpage.component.html',
  styleUrl: './registerpage.component.scss'
})
export class RegisterpageComponent {
  equalsPassword: boolean = false;
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = this.fb.group({
      imageProfile: [null],
      name: [''],
      email: [''],
      nationality: [''],
      phone: [''],
      whatsapp: [''],
      dateOfBirth: [''],
      city: [''],
      gender: [''],
      firstPassword: [''],
      password: ['']
    });
  }

  submit() {
    if (!this.equalsPassword) {
      alert("As senhas não são iguais!" + this.equalsPassword);
      return;
    }

    if (this.form.valid) {
      const formData = new FormData();
      formData.set('imageProfile', this.form.get('imageProfile')?.value);
      formData.set('name', this.form.get('name')?.value);
      formData.set('email', this.form.get('email')?.value);
      formData.set('nationality', this.form.get('nationality')?.value);
      formData.set('phone', this.form.get('phone')?.value);
      formData.set('whatsapp', this.form.get('whatsapp')?.value);
      formData.set('dateOfBirth', this.form.get('dateOfBirth')?.value);
      formData.set('city', this.form.get('city')?.value);
      formData.set('gender', this.form.get('gender')?.value);
      formData.set('password', this.form.get('password')?.value);

      formData.forEach((value, key) => {
        console.log(key, value);
      });

      this.userService.registerUser(formData).subscribe({
        next: (response: HttpResponse<any>) => {
          this.authService.configureLocalStorage(response.body)
          alert("Cadastro realizado com sucesso!");
          this.router.navigate(["/"]);
        },
        error: (error) => {
          alert("Erro ao tentar realizar cadastro! Erro: " + error.message);
          console.log(error);
        }
      })
    } else {
      alert("Preencha os campos corretamente!");
    }
  }

  verifyPassword(event: Event): void {
    const firstPassword = this.form.get('firstPassword')?.value;
    const password = this.form.get('password')?.value;
   
    if (firstPassword === password) {
      this.equalsPassword = true;
    } else {
      this.equalsPassword = false;
      console.log("As senhas digitadas não são iguais." + firstPassword + ', ' + password)
    }
  }

  onFileSelect(event: Event): void {
    const element = event.currentTarget as HTMLInputElement;
    let file: File | null = element.files ? element.files[0] : null;
    if (file) {
      this.form.patchValue({ imageProfile: file })
      this.form.get('imageProfile')?.updateValueAndValidity();
    }
  }
}
