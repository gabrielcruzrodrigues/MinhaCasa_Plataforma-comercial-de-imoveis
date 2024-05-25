import { Component } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [NavbarComponent],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent {
  name: string = 'Gabriel Cruz Rodrigues';
  idade: string = '22 anos';
  phone: string  = '73988484848';
  whatsapp: string = '73988484848';
  email: string = 'email@gmail.com';
  state: string = 'Bahia';
  city: string = 'Jequié';
}
