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
  showDeleteButtons: boolean[] = [];

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
    this.showDeleteButtons = new Array(this.images.length).fill(false);
  }

  showDeleteButton(index: number): void {
    this.showDeleteButtons[index] = true;
  }

  hideDeleteButton(index: number): void {
    this.showDeleteButtons[index] = false;
  }

  deleteImage(index: number): void {
    this.images.splice(index, 1);
    this.imageUrls.splice(index, 1);
    this.showDeleteButtons.splice(index, 1);
  }
}
