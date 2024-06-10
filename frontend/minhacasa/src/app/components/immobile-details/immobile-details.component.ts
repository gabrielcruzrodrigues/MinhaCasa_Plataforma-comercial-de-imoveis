import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { ArrowCarroselComponent } from '../layout/arrow-carrosel/arrow-carrosel.component';
import { ImmobileService } from '../../services/immobile.service';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { UserService } from '../../services/user.service';
import { LoadingComponent } from '../layout/loading/loading.component';
import { FooterComponent } from '../layout/footer/footer.component';
import { ConverterAgeToPortuguese } from '../../utils/ConverterAgeToPortuguese';
import { ConverterCategoryToPortuguese } from '../../utils/ConverterCategoryToPortuguese';
import { ConverterTypeForPortuguese } from '../../utils/ConverterTypeToPortuguese';
import { ConverterSellerTypeToPortuguese } from '../../utils/ConverterSellerTypeToPortuguese';
import { GetTrueBooleanFields } from '../../utils/GetTrueBooleanFields';

@Component({
  selector: 'app-immobile-details',
  standalone: true,
  imports: [NavbarComponent, ArrowCarroselComponent, CommonModule, LoadingComponent, FooterComponent],
  templateUrl: './immobile-details.component.html',
  styleUrl: './immobile-details.component.scss'
})
export class ImmobileDetailsComponent implements OnInit{
  imagesUrl: string[] = [];
  immobileId: string | null = null;
  sellerId: string | null = null;
  isLoading: boolean = false;

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

  trueBooleanFields: string[] = [];

  constructor(  
    private immobileService: ImmobileService, 
    private route: ActivatedRoute,
    private currencyPipe: CurrencyPipe,
    private userService: UserService
  ){}

  ngOnInit(): void {
    this.isLoading = true;
    this.immobileId = this.route.snapshot.paramMap.get('id');
    this.sellerId = this.route.snapshot.paramMap.get('seller-id');

    this.immobileService.getImmobileWithCompleteImagesPath(this.immobileId).subscribe({
      next: (response: HttpResponse<any>) => {
        this.populateFields(response.body);

        this.userService.findByIdForProfile(this.sellerId).subscribe({
          next: (response: HttpResponse<any>) => {
            this.sellerImage = response.body.imageProfileUrl;
            this.sellerName = response.body.name;
            this.sellerWhatsapp = response.body.whatsapp;
            this.sellerWhatsappLink = `https://wa.me/55${this.sellerWhatsapp}?text=Olá! Estou interessado(a) pela publicação "${this.name}", vim pelo minhacasa.com`;
            this.isLoading = false;
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
    this.garden = this.verifyBoolean(body.garden);

    if (body.totalArea == null) {
      this.totalArea = 'não especificado'
    } else {
      this.totalArea = `${body.totalArea}m²`;
    }

    if (body.usefulArea == null) {
      this.usefulArea = 'não especificado'
    } else {
      this.usefulArea = `${body.usefulArea}m²`;
    }

    if (body.iptu == null) {
      this.IPTU = 'não especificado'
    } else {
      this.IPTU = `R$ ${body.iptu}`;
    }
    
    this.category = ConverterCategoryToPortuguese.converter(body.category);
    this.age = ConverterAgeToPortuguese.converter(body.age);
    this.type = ConverterTypeForPortuguese.converter(body.type);
    this.sellerType = ConverterSellerTypeToPortuguese.converter(body.sellerType);
    this.trueBooleanFields = GetTrueBooleanFields.get(body);
  }

  verifyBoolean(variable : any): string {
    return (variable) ? 'sim' : 'não';
  }

  formatPrice(price: string): string {
    const numericPrice = parseFloat(price);
    return this.currencyPipe.transform(numericPrice, 'BRL', 'symbol', '1.0-0') ?? '';
  }
}
