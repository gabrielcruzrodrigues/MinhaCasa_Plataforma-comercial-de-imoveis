import { CommonModule, CurrencyPipe  } from '@angular/common';
import {Component, Input} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, CommonModule],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent {
  isLoading: boolean = true;
  @Input() id: string = '';
  @Input() quantityRooms: string = '';
  @Input() quantityBedrooms: string = '';
  @Input() quantityBathrooms: string = '';
  @Input() imageUrl: string = '';
  @Input() price: string = '';
  @Input() name: string = '';

  constructor(private currencyPipe: CurrencyPipe, private router: Router) {}

  isFavorited: boolean = false;

  toggleFavorite() {
    this.isFavorited = !this.isFavorited;
  }

  formatPrice(price: string): string {
    const numericPrice = parseFloat(price);
    return this.currencyPipe.transform(numericPrice, 'BRL', 'symbol', '1.0-0') ?? '';
  }

  redirectForImmobileDetails():void {
    this.router.navigate(["/immobile/" + this.id + "/" + this.name]);
  }
}
