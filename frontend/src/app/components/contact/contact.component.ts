import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { Router, RouterConfigOptions } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [NavbarComponent, FormsModule],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss'
})
export class ContactComponent implements OnInit{

  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
  ){
    this.form = this.fb.group({
      text: ['', Validators.required],
      type: ['', Validators.required],
      senderName: ['', Validators.required],
      senderId: ['', Validators.required]
    })
  }

  ngOnInit(): void {
    const id = this.authService.getUserId();
    this.form.patchValue({
      senderId: id
    })
  }

  selectedOption: string = '';

  selectOption(option: string) {
    this.selectedOption = option;
  }
}
