import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../layout/footer/footer.component';
import { CardComponent } from '../layout/card/card.component';
import { CommonModule } from '@angular/common';
import { ImmobileService } from '../../services/immobile.service';
import { HttpResponse } from '@angular/common/http';
import { NavbarComponent } from '../layout/navbar/navbar.component';

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
  selector: 'app-favorites',
  standalone: true,
  imports: [
    FooterComponent, CardComponent, CommonModule, NavbarComponent
  ],
  templateUrl: './favorites.component.html',
  styleUrl: './favorites.component.scss'
})
export class FavoritesComponent implements OnInit{
  cards: cardInterface[] =[];
  isLoading: boolean = true;

  constructor(
    private immobileService: ImmobileService
  ) {}

  ngOnInit(): void {
    this.immobileService.searchForUserFavoriteImmobiles().subscribe({
      next: (response: HttpResponse<any>) => {
        if (response.status === 200) {
          this.cards = response.body;
          this.isLoading = false;
        } else {
          console.log("Um status code diferente do esperado foi retornado ao tentar buscar cards favoritos.");
        }
      },
      error: () => {
        console.log("Aconteceu um erro ao tentar buscar os imóveis favoritos do usuário.");
      }
    })
  }
}
