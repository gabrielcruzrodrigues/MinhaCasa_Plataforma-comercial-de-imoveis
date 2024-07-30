import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { CardComponent } from '../layout/card/card.component';
import { FooterComponent } from '../layout/footer/footer.component';
import { ImmobileService } from '../../services/immobile.service';
import { HttpResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';

interface cardInterface {
  id: string,
  quantityRooms: string,
  quantityBedrooms: string,
  quantityBathrooms: string,
  imageUrl: string,
  price: string,
  name: string,
  sellerId: string
}

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [
    NavbarComponent, CardComponent, FooterComponent, CommonModule
  ],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent implements OnInit{
  immobiles: cardInterface[] = [];

  constructor (private immobileService: ImmobileService) {}

  ngOnInit(): void {
    this.immobileService.find4immobilesForHome().subscribe({
      next: (response: HttpResponse<any>) => {
        this.immobiles = response.body;
      },
      error: (error) => {
        console.log(error);
      }
    })
  }
  

}
