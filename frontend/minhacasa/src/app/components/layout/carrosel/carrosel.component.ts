import { CommonModule } from '@angular/common';
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-carrosel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './carrosel.component.html',
  styleUrl: './carrosel.component.scss'
})
export class CarroselComponent implements OnInit, OnChanges{
  @Input() images: File[] = [];
  imageUrls: string[] = [];

  ngOnInit(): void {
    this.loadImageUrls();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['images']) {
      this.loadImageUrls();
    }
  }

  loadImageUrls(): void {
    this.imageUrls = this.images.map(image => URL.createObjectURL(image));
  }
}
