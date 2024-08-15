import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { ArrowCarroselComponent } from '../layout/arrow-carrosel/arrow-carrosel.component';
import { ImmobileService } from '../../services/immobile.service';
import { ActivatedRoute, Router } from '@angular/router';
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
import { ModalTextComponent } from '../layout/modal-text/modal-text.component';
import { NavbarComponent } from '../layout/navbar/navbar.component';

@Component({
  selector: 'app-immobile-details',
  standalone: true,
  imports: [ArrowCarroselComponent, CommonModule, LoadingComponent, FooterComponent, ModalTextComponent, NavbarComponent],
  templateUrl: './immobile-details.component.html',
  styleUrl: './immobile-details.component.scss'
})
export class ImmobileDetailsComponent implements OnInit{
  imagesUrl: string[] = [];
  immobileId: string | null = null;
  sellerId: string | null = null;
  userId: string | null | undefined = null;
  isLoading: boolean = false;
  showModalText: boolean = false;
  message: string = '';
  @ViewChild(ModalTextComponent) modalComponent!: ModalTextComponent;
  isSeller: boolean = false;

  id: string = '';
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
  immobileSellerId: string = '';

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
    private userService: UserService,
    private cdr: ChangeDetectorRef,
    private router: Router,
  ){}

  ngOnInit(): void {
    this.isLoading = true;
    this.immobileId = this.route.snapshot.paramMap.get('id');
    this.sellerId = this.route.snapshot.paramMap.get('seller-id');

    this.userId = this.userService.getIdOfTheUserLogged();

    this.immobileService.getImmobileWithCompleteImagesPath(this.immobileId).subscribe({
      next: (response: HttpResponse<any>) => {
        this.immobileSellerId = response.body.sellerId;
        this.populateFields(response.body.immobile);

        this.userService.findByIdForProfile(this.sellerId).subscribe({
          next: (response: HttpResponse<any>) => {
            this.sellerImage = response.body.imageProfileUrl;
            this.sellerName = response.body.name;
            this.sellerWhatsapp = response.body.whatsapp;
            this.sellerWhatsappLink = `https://wa.me/55${this.sellerWhatsapp}?text=Olá! Estou interessado(a) pela publicação "${this.name}", vim pelo minhacasa.com`;
            this.isLoading = false;

            if (this.immobileSellerId == this.userId) {
              this.isSeller = true;
            }
          }
        })
      },
      error: (error) => {
        console.log('Erro ao tentar buscar o immovel: ', error);
      }
    })
  }
  
  populateFields(body: any): void {
    console.log(body);
    this.id = body.id;
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

  activeModalText(text: string):void {
    this.showModalText = false; //reset
    setTimeout(() => {
      this.message = text;
      this.showModalText = true;
      this.cdr.detectChanges();
    });
  }

  waitForModalClose(): Promise<void> {
    return new Promise(resolve => {
      this.modalComponent.onClose.subscribe(() => resolve());
    })
  }

  soldImmobile(): void {
    this.immobileService.soldImmobile(this.id).subscribe({
      next: (response: HttpResponse<any>) => {
        console.log('oi');
        if (response.status === 200) {
          this.activeModalText('Imóvel marcado como vendido!');
          this.waitForModalClose().then(() => {
            this.router.navigate(["/profile"]);
          });
        } else {
          this.activeModalText("Aconteceu um pequeno imprevisto, por favor tente mais tarde!");
        }
      },
      error: (error) => {
        this.activeModalText("Aconteceu um pequeno imprevisto, por favor tente mais tarde!");
        console.log(`Erro ao tentar marcar o imóvel como vendido: ${error.message}`);
      }
    })
  }
}
