import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { ArrowCarroselComponent } from '../layout/arrow-carrosel/arrow-carrosel.component';
import { ImmobileService } from '../../services/immobile.service';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { CurrencyPipe } from '@angular/common';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-immobile-details',
  standalone: true,
  imports: [NavbarComponent, ArrowCarroselComponent],
  templateUrl: './immobile-details.component.html',
  styleUrl: './immobile-details.component.scss'
})
export class ImmobileDetailsComponent implements OnInit{
  imagesUrl: string[] = [];
  immobileId: string | null = null;
  sellerId: string | null = null;

  name: string = '';
  address: string = '';
  state: string = '';
  city: string = '';
  price: string = '';
  category: string = '';
  quantityRooms: string = '';
  quantityBedrooms: string = '';
  quantityBathrooms: string = '';

  //seller
  sellerImage: string = '';
  sellerName: string = '';

  constructor(
    private immobileService: ImmobileService, 
    private route: ActivatedRoute,
    private currencyPipe: CurrencyPipe,
    private userService: UserService
  ){}

  ngOnInit(): void {
    this.immobileId = this.route.snapshot.paramMap.get('id');
    this.sellerId = this.route.snapshot.paramMap.get('seller-id');

    this.immobileService.getImmobileWithCompleteImagesPath(this.immobileId).subscribe({
      next: (response: HttpResponse<any>) => {
        this.populateFields(response.body);
        console.log(this.sellerId)

        this.userService.findByIdForProfile(this.sellerId).subscribe({
          next: (response: HttpResponse<any>) => {
            console.log(response)
            this.sellerImage = response.body.imageProfileUrl;
            this.sellerName = response.body.name;
          }
        })
      },
      error: (error) => {
        console.log('Erro ao tentar buscar o immovel: ', error);
      }
    })
  }
  
  populateFields(body: any): void {
    this.imagesUrl = body.files;
    this.name = body.name;
    this.address = body.address;
    this.state = body.state;
    this.city = body.city;
    this.price = this.formatPrice(body.price);
    this.quantityRooms = body.quantityRooms;
    this.quantityBedrooms = body.quantityBedrooms;
    this.quantityBathrooms = body.quantityBathrooms;

    let category = body.category;

    switch (category) {
      case 'SELL':
        this.category = 'vender';
        break;
      case "RENT":
        this.category = 'alugar';
        break;
      case "TEMPORARY_RENTAL":
        this.category = "alugar por temporada";
        break;
      case "FINANCING":
        this.category = "financiamento";
        break;
    }
  }

  formatPrice(price: string): string {
    const numericPrice = parseFloat(price);
    return this.currencyPipe.transform(numericPrice, 'BRL', 'symbol', '1.0-0') ?? '';
  }
}
