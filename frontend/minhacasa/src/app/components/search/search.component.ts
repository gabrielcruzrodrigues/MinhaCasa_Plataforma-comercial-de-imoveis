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
      minPrice: [''], //
      maxPrice: [''], //
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
    console.log(this.form.value);
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
    const formValues = this.form.value;

  Object.keys(formValues).forEach(key => {
      if (formValues[key] !== null && formValues[key] !== undefined) {
        this.formData.set(key, formValues[key]);
      }
    });
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

  verifyIfExistsState():void {
    // alert('oi')
    if (this.form.get('state')?.value === '') {
      this.activeModalText("Selecione um estado para escolher uma cidade!");
    }
  }

  clearForm():void {
    this.setInitialFormValues();
    this.submitSearch();
  }

  setInitialFormValues(): void {
    this.form.patchValue({
      pageNumber: 1,
      pageSize: 12,
      name: '',
      city: '',
      neighborhood: '',
      state: '',
      garage: false,
      quantityBedrooms: '',
      quantityRooms: '',
      iptu: '',
      minPrice: '',
      maxPrice: '',
      suite: false,
      totalArea: '',
      quantityBathrooms: '',
      integrity: '',
      sellerType: '',
      age: '',
      category: '',
      type: '',
      garden: false,
      virtualTour: false,
      videos: false,
      beach: false,
      disabledAccess: false,
      playground: false,
      grill: false,
      energyGenerator: false,
      closeToTheCenter: false,
      elevator: false,
      pool: false,
      frontDesk: false,
      multiSportsCourt: false,
      gym: false,
      steamRoom: false,
      cableTV: false,
      heating: false,
      cabinetsInTheKitchen: false,
      bathroomInTheRoom: false,
      internet: false,
      partyRoom: false,
      airConditioning: false,
      americanKitchen: false,
      hydromassage: false,
      fireplace: false,
      privatePool: false,
      electronicGate: false,
      serviceArea: false,
      pub: false,
      closet: false,
      office: false,
      yard: false,
      alarmSystem: false,
      balcony: false,
      concierge24Hour: false,
      walledArea: false,
      dogAllowed: false,
      catAllowed: false,
      cameras: false,
      furnished: false,
      seaView: false,
      gatedCommunity: false
    });
  }
}

