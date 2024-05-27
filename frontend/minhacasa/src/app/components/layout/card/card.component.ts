import { CommonModule, CurrencyPipe  } from '@angular/common';
import {Component, Input} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, CommonModule],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent {
  isLoading: boolean = true;
  @Input() quantityRooms: string = '';
  @Input() quantityBedrooms: string = '';
  @Input() quantityBathrooms: string = '';
  @Input() imageProfileBase64: string = '';
  @Input() price: string = '';
  @Input() name: string = '';

  constructor(private currencyPipe: CurrencyPipe) {}

  imageLoaded() {
    this.isLoading = true;
  }

  formatPrice(price: string): string {
    const numericPrice = parseFloat(price);
    return this.currencyPipe.transform(numericPrice, 'BRL', 'symbol', '1.0-0') ?? '';
  }
}
