import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { UserService } from '../../services/user.service';
import { HttpResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CardComponent } from '../layout/card/card.component';
import { FooterComponent } from '../layout/footer/footer.component';

interface propertiesInterface {
  quantityRooms: string,
  quantityBedrooms: string,
  quantityBathrooms: string,
  imageProfileBase64: string,
  price: string,
  name: string
}

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [NavbarComponent, CommonModule, CardComponent, FooterComponent],
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

  immobiles: propertiesInterface[] = [];

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
              this.immobiles = response.body.immobiles;
            }
          },
          error: (error) => {
            console.log('aconteceu um erro ao tentar buscar o usuário pelo id!');
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
