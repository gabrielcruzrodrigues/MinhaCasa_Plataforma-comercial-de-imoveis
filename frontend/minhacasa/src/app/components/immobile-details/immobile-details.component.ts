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
  description: string = '';
  neighborhood: string = '';
  garage: string = '';
  suite: string = '';
  age: string = '';
  type: string = '';
  sellerType: string = '';
  totalArea: string = '';
  usefulArea: string = '';
  IPTU: string = '';
  garden: string = '';

  //seller
  sellerImage: string = '';
  sellerName: string = '';
  sellerWhatsapp: string = '';
  sellerWhatsappLink: string = '';

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
            this.sellerWhatsapp = response.body.whatsapp;
            this.sellerWhatsappLink = `https://wa.me/55${this.sellerWhatsapp}?text=Olá! Estou interessado(a) pela publicação "${this.name}", vim pelo minhacasa.com`;
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
    this.description = body.description;
    this.neighborhood = body.neighborhood;
    this.garage = this.verifyBoolean(body.garage);
    this.suite = this.verifyBoolean(body.suite);
    this.type = body.type;
    this.totalArea = body.totalArea;
    this.usefulArea = body.usefulArea;
    this.IPTU = body.iptu;
    this.garden = this.verifyBoolean(body.garden);

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

    let age = body.age;

    switch(age) {
      case 'IN_THE_PLANT':
        this.age = 'na planta';
        break;
      case 'BUILDING':
        this.age = 'construindo';
        break;
      case 'UP_TO_1_YEARS':
        this.age = 'mais ou menos 1 ano';
        break;
      case 'UP_TO_2_YEARS':
        this.age = 'mais de 2 anos';
        break;
      case 'UP_TO_5_YEARS':
        this.age = 'mais de 5 anos';
        break;
      case 'UP_TO_10_YEARS':
        this.age = 'mais de 10 anos';
        break;
      case 'UP_TO_20_YEARS':
        this.age = 'mais de 20 anos';
        break;
      case 'UP_TO_30_YEARS':
        this.age = 'mais de 30 anos';
        break;
      case 'UP_TO_40_YEARS':
        this.age = 'mais de 40 anos';
        break;
      case 'OVER_50_YEARS':
        this.age = 'mais de 50 anos';
        break;
      default:
        this.age = 'idade não definida';
    }

    switch (body.type) {
      case "HOUSE":
        this.type = "Casa";
        break;
      case "ROOM":
          this.type = "Quarto";
          break;
      case "ROOF":
          this.type = "Cobertura";
          break;
      case "FLAT":
          this.type = "Apartamento";
          break;
      case "KITNET":
          this.type = "Kitnet";
          break;
      case "LOFT":
          this.type = "Loft";
          break;
      case "STUDIO":
          this.type = "Estúdio";
          break;
      case "DUPLEX":
          this.type = "Duplex";
          break;
      case "TRIPLEX":
          this.type = "Triplex";
          break;
      case "CONDOMINIUM":
          this.type = "Condomínio";
          break;
      case "BUILDING":
          this.type = "Prédio";
          break;
      case "SHEDS":
          this.type = "Galpões";
          break;
      case "DEPOSITS":
          this.type = "Depósitos";
          break;
      case "OFFICES":
          this.type = "Escritórios";
          break;
      case "PARKING":
          this.type = "Estacionamento";
          break;
      case "STORE":
          this.type = "Loja";
          break;
      case "SUBDIVISION":
          this.type = "Loteamento";
          break;
      case "GATED_COMMUNITY":
          this.type = "Condomínio Fechado";
          break;
      case "FARM":
          this.type = "Fazenda";
          break;
      default:
          this.type = "Tipo não especificado";
    }

    switch (body.sellerType) {
      case 'OWNER':
        this.sellerType = 'proprietário';
        break;
      case 'BROKER':
        this.sellerType = 'corretora';
        break;
      case 'REAL_ESTATE':
        this.sellerType = 'imobiliária';
        break;
      default:
        this.sellerType = 'vendedor não definido';
    }
  }

  verifyBoolean(variable : any): string {
    return (variable) ? 'sim' : 'não';
  }

  formatPrice(price: string): string {
    const numericPrice = parseFloat(price);
    return this.currencyPipe.transform(numericPrice, 'BRL', 'symbol', '1.0-0') ?? '';
  }
}
