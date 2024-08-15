import { CommonModule, CurrencyPipe  } from '@angular/common';
import {Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../../services/user.service';
import { HttpResponse } from '@angular/common/http';
import { AuthService } from '../../../services/auth.service';
import { ImmobileService } from '../../../services/immobile.service';
import { LoadingComponent } from '../loading/loading.component';
import { ImmobileDetailsComponent } from "../../immobile-details/immobile-details.component";

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [CommonModule, LoadingComponent, ImmobileDetailsComponent],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent implements OnInit{
  isLoading: boolean = true;
  @Input() type: string = '';
  @Input() id: string = '';
  @Input() quantityRooms: string = '';
  @Input() quantityBedrooms: string = '';
  @Input() quantityBathrooms: string = '';
  @Input() imageUrl: string = '';
  @Input() price: string = '';
  @Input() name: string = '';
  @Input() sellerId: string = '';
  ownerAccess: boolean = false;

  constructor(
    private currencyPipe: CurrencyPipe, 
    private router: Router,
    private userService: UserService,
    private authService: AuthService,
    private immobileService: ImmobileService
  ) {}

  isFavorited: boolean = false;
  idsFavorited: number[] = [];

  ngOnInit(): void {
    if (this.type === "owner") {
      this.ownerAccess = true;
    }

    if (this.authService.verifyIfAreLoggedIn()) {
      this.immobileService.searchIdsOfImmobilesFavorited().subscribe({
        next: (response: HttpResponse<any>) => {
          console.log('passou aqui!');
          if (response.status === 200) {
            this.idsFavorited = response.body;
            this.verifyIds();
          } 
          if (response.status === 401) {
            this.authService.clearLocalStorage();
            this.isLoading = false;
          }
        },
        error: (error) => {
          this.authService.clearLocalStorage();
          this.isLoading = false;
          console.log("Ocorreu um erro ao verificar se o card esta favoritado.");
        }
      })
    } else {
      this.authService.clearLocalStorage();
      this.isLoading = false;
    }
  }

  verifyIds(): void {
    if (this.idsFavorited.includes(Number(this.id))) {
      this.isFavorited = true;
    }
    this.isLoading = false;
  }

  toggleFavorite() {
    if (!this.authService.verifyIfAreLoggedIn()) {
      this.router.navigate(['/login']);
    }

    this.isFavorited = !this.isFavorited;
    this.changeFavorited();
  }

  changeFavorited():void { 
    if (this.isFavorited) {
      this.userService.addFavoriteImmobile(this.id).subscribe({
        next: (response: HttpResponse<any>) => {
          if (response.status !== 204) {
            console.log("a resposta do servidor foi diferente do esperado ao tentar favoritar imóvel.");
          }
        },
        error: () => {
          console.log("erro ao tentar favoritar imóvel.");
        }
      })
    } else {
      this.userService.removeFavoriteImmobile(this.id).subscribe({
        next: (response: HttpResponse<any>) => {
          if (response.status !== 204) {
            console.log("a resposta do servidor foi diferente do esperado ao tentar desfavoritar imóvel.");
          }
        },
        error: () => {
          console.log("erro ao tentar desfavoritar imóvel.");
        }
      })
    }
  }

  formatPrice(price: string): string {
    const numericPrice = parseFloat(price);
    return this.currencyPipe.transform(numericPrice, 'BRL', 'symbol', '1.0-0') ?? '';
  }

  redirectForImmobileDetails():void {
    this.router.navigate(["/immobile/" + this.id + "/" + this.name + "/" + this.sellerId]);
  }

  redirectForUpdatePage(): void {
    this.router.navigate(["/update-immobile/" + this.id]);
  }

  soldImmobile(): void {

  }
}
