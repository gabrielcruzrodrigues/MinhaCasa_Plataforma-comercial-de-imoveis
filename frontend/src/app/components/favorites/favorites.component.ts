import { Component } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { FooterComponent } from '../layout/footer/footer.component';
import { CardComponent } from '../layout/card/card.component';
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
  selector: 'app-favorites',
  standalone: true,
  imports: [
    NavbarComponent, FooterComponent, CardComponent, CommonModule
  ],
  templateUrl: './favorites.component.html',
  styleUrl: './favorites.component.scss'
})
export class FavoritesComponent {
  cards: cardInterface[] =[];
  isLoading: boolean = true;
}
