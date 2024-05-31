import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-arrow-carrosel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './arrow-carrosel.component.html',
  styleUrl: './arrow-carrosel.component.scss'
})
export class ArrowCarroselComponent {
  @Input() imageUrls: string[] = [];

  currentIndex: number = 0;

  constructor() { }

  ngOnInit(): void { }

  prevImage() {
    if (this.currentIndex > 0) {
      this.currentIndex--;
    } else {
      this.currentIndex = this.imageUrls.length - 1; // Loop para o final
    }
    this.updateCarousel();
  }

  nextImage() {
    if (this.currentIndex < this.imageUrls.length - 1) {
      this.currentIndex++;
    } else {
      this.currentIndex = 0; // Loop para o início
    }
    this.updateCarousel();
  }

  updateCarousel() {
    const carouselImages = document.querySelector('.carousel-images') as HTMLElement;
    const offset = this.currentIndex * 210;
    carouselImages.style.transform = `translateX(-${offset}px)`;
  }

  getTransform() {
    const offset = this.currentIndex * 210;
    return `translateX(-${offset}px)`;
  }
}
