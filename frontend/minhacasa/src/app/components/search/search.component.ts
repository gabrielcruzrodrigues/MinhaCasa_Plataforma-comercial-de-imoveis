import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { CardComponent } from '../layout/card/card.component';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ImmobileService } from '../../services/immobile.service';
import { HttpResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { LoadingComponent } from '../layout/loading/loading.component';
import { CepService } from '../../services/cep.service';
import { ModalTextComponent } from '../layout/modal-text/modal-text.component';
import { PaginatorComponent } from '../layout/paginator/paginator.component';

interface cardInterface {
  id: string,
  quantityRooms: string,
  quantityBedrooms: string,
  quantityBathrooms: string,
  imageUrl: string,
  price: string,
  name: string,
  sellerId: string
}

interface city {
  nome: string;
}

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    NavbarComponent, CardComponent, FormsModule, ReactiveFormsModule, CommonModule, LoadingComponent,
    ModalTextComponent, PaginatorComponent
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss'
})
export class SearchComponent implements OnInit {
  form: FormGroup;
  formData = new FormData();
  pageNumber: number = 1;
  pageSize: number = 10;
  cards: cardInterface[] =[];
  isLoading: boolean = true;

  cities: string[] = [];
  showModalText: boolean = false;
  message: string = '';
  @ViewChild(ModalTextComponent) modalComponent!: ModalTextComponent;

  constructor(
    private fb: FormBuilder,
    router: Router,
    private immobileService: ImmobileService,
    private cepService: CepService,
    private cdr: ChangeDetectorRef
  ) {
    this.form = this.fb.group({
      pageNumber: [1], //
      pageSize: [12], //
      name: [''], //
      city: [''], //
      neighborhood: [''], //
      state: [''], //
      garage: [false],
      quantityBedrooms: [''], //
      quantityRooms: [''], //
      iptu: [''], //
      price: [''], //
      suite: [false],
      totalArea: [''], //
      quantityBathrooms: [''], //
      integrity: [''],
      sellerType: [''], //
      age: [''],  //
      category: [''],
      type: [''], //
      garden: [false],
      virtualTour: [false],
      videos: [false],
      beach: [false],
      disabledAccess: [false],
      playground: [false],
      grill: [false],
      energyGenerator: [false],
      closeToTheCenter: [false],
      elevator: [false],
      pool: [false],
      frontDesk: [false],
      multiSportsCourt: [false],
      gym: [false],
      steamRoom: [false],
      cableTV: [false],
      heating: [false],
      cabinetsInTheKitchen: [false],
      bathroomInTheRoom: [false],
      internet: [false],
      partyRoom: [false],
      airConditioning: [false],
      americanKitchen: [false],
      hydromassage: [false],
      fireplace: [false],
      privatePool: [false],
      electronicGate: [false],
      serviceArea: [false],
      pub: [false],
      closet: [false],
      office: [false],
      yard: [false],
      alarmSystem: [false],
      balcony: [false],
      concierge24Hour: [false],
      walledArea: [false],
      dogAllowed: [false],
      catAllowed: [false],
      cameras: [false],
      furnished: [false],
      seaView: [false],
      gatedCommunity: [false]
    });
  }

  ngOnInit(): void {

    this.populateFormData();
    this.immobileService.search(this.formData).subscribe({
      next: (response: HttpResponse<any>) => {
        this.cards = response.body;
        console.log(this.cards);
        this.isLoading = false;
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  submitSearch(): void {
    this.isLoading = true;
    this.cards = [];
    this.populateFormData();
    this.immobileService.search(this.formData).subscribe({
      next: (response: HttpResponse<any>) => {
        this.cards = response.body;
        console.log(this.cards);
        this.isLoading = false;
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  populateFormData(): void {
    this.formData.set('pageNumber', this.form.get('pageNumber')?.value);
    this.formData.set('pageSize', this.form.get('pageSize')?.value);
    this.formData.set('name', this.form.get('name')?.value);
    this.formData.set('city', this.form.get('city')?.value);
    this.formData.set('neighborhood', this.form.get('neighborhood')?.value);
    this.formData.set('state', this.form.get('state')?.value);
    this.formData.set('garage', this.form.get('garage')?.value);
    this.formData.set('quantityBedrooms', this.form.get('quantityBedrooms')?.value);
    this.formData.set('quantityRooms', this.form.get('quantityRooms')?.value);
    this.formData.set('iptu', this.form.get('iptu')?.value);
    this.formData.set('price', this.form.get('price')?.value);
    this.formData.set('suite', this.form.get('suite')?.value);
    this.formData.set('totalArea', this.form.get('totalArea')?.value);
    this.formData.set('quantityBathrooms', this.form.get('quantityBathrooms')?.value);
    this.formData.set('integrity', this.form.get('integrity')?.value);
    this.formData.set('sellerType', this.form.get('sellerType')?.value);
    this.formData.set('age', this.form.get('age')?.value);
    this.formData.set('category', this.form.get('category')?.value);
    this.formData.set('type', this.form.get('type')?.value);
    this.formData.set('garden', this.form.get('garden')?.value);
    this.formData.set('virtualTour', this.form.get('virtualTour')?.value);
    this.formData.set('videos', this.form.get('videos')?.value);
    this.formData.set('beach', this.form.get('beach')?.value);
    this.formData.set('disabledAccess', this.form.get('disabledAccess')?.value);
    this.formData.set('playground', this.form.get('playground')?.value);
    this.formData.set('grill', this.form.get('grill')?.value);
    this.formData.set('energyGenerator', this.form.get('energyGenerator')?.value);
    this.formData.set('closeToTheCenter', this.form.get('closeToTheCenter')?.value);
    this.formData.set('elevator', this.form.get('elevator')?.value);
    this.formData.set('pool', this.form.get('pool')?.value);
    this.formData.set('frontDesk', this.form.get('frontDesk')?.value);
    this.formData.set('multiSportsCourt', this.form.get('multiSportsCourt')?.value);
    this.formData.set('gym', this.form.get('gym')?.value);
    this.formData.set('steamRoom', this.form.get('steamRoom')?.value);
    this.formData.set('cableTV', this.form.get('cableTV')?.value);
    this.formData.set('heating', this.form.get('heating')?.value);
    this.formData.set('cabinetsInTheKitchen', this.form.get('cabinetsInTheKitchen')?.value);
    this.formData.set('bathroomInTheRoom', this.form.get('bathroomInTheRoom')?.value);
    this.formData.set('internet', this.form.get('internet')?.value);
    this.formData.set('partyRoom', this.form.get('partyRoom')?.value);
    this.formData.set('airConditioning', this.form.get('airConditioning')?.value);
    this.formData.set('americanKitchen', this.form.get('americanKitchen')?.value);
    this.formData.set('hydromassage', this.form.get('hydromassage')?.value);
    this.formData.set('fireplace', this.form.get('fireplace')?.value);
    this.formData.set('privatePool', this.form.get('privatePool')?.value);
    this.formData.set('electronicGate', this.form.get('electronicGate')?.value);
    this.formData.set('serviceArea', this.form.get('serviceArea')?.value);
    this.formData.set('pub', this.form.get('pub')?.value);
    this.formData.set('closet', this.form.get('closet')?.value);
    this.formData.set('office', this.form.get('office')?.value);
    this.formData.set('yard', this.form.get('yard')?.value);
    this.formData.set('alarmSystem', this.form.get('alarmSystem')?.value);
    this.formData.set('balcony', this.form.get('balcony')?.value);
    this.formData.set('concierge24Hour', this.form.get('concierge24Hour')?.value);
    this.formData.set('walledArea', this.form.get('walledArea')?.value);
    this.formData.set('dogAllowed', this.form.get('dogAllowed')?.value);
    this.formData.set('catAllowed', this.form.get('catAllowed')?.value);
    this.formData.set('cameras', this.form.get('cameras')?.value);
    this.formData.set('furnished', this.form.get('furnished')?.value);
    this.formData.set('seaView', this.form.get('seaView')?.value);
    this.formData.set('gatedCommunity', this.form.get('gatedCommunity')?.value);
  }

  findCitiesByState(event: Event) {
    const selectedState = this.form.get('state')?.value;
    this.cities = [];
    
    this.cepService.findCities(selectedState).subscribe({
      next: (response: HttpResponse<any>) => {
        console.log(response);
        if (response.body && Array.isArray(response.body)) {
          this.cities = response.body.map((city: city) => city.nome)
            .sort((a, b) => a.localeCompare(b));
        }
      },
      error: (error) => {
        this.activeModalText("Aconteceu um erro interno, Por favor tente mais tarde.");
        console.log('Erro ao buscar cidades por UF: ', error);
      }
    });
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
}
