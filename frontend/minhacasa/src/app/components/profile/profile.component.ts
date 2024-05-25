import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { UserService } from '../../services/user.service';
import { response } from 'express';
import { HttpResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [NavbarComponent, CommonModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit{
  name: string = '';
  age: string = '';
  phone: string  = '';
  whatsapp: string = '';
  email: string = '';
  state: string = '';
  city: string = '';
  imageProfileUrl: string = '';

  constructor(
    private userService:UserService
  ) {}

  ngOnInit(): void {
      const id = this.userService.getIdOfTheUserLogged();
      if (id) {
        this.userService.findByIdForProfile(id).subscribe({
          next: (response: HttpResponse<any>) => {
            if (response.status === 200) {
              this.populateFilds(response.body);
            }
          },
          error: (error) => {
            console.log('aconteceu um erro ao tentar buscar o usuário pelo id!');
            console.log(error);
          }
        })
      }
  }

  populateFilds(body: any):void {
    this.name = body.name;
    this.age = body.dateOfBirth;
    this.phone = body.phone;
    this.whatsapp = body.whatsapp;
    this.email = body.email;
    this.state = body.state;
    this.city = body.city;

    if (body.imageProfileBase64) {
      this.imageProfileUrl = 'data:image/jpeg;base64,' + body.imageProfileBase64;
    }
  }
}
