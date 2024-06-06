import { ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { FooterComponent } from '../layout/footer/footer.component';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ModalAlertComponent } from '../layout/modal-alert/modal-alert.component';
import { ModalTextComponent } from '../layout/modal-text/modal-text.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CepService } from '../../services/cep.service';
import { HttpResponse } from '@angular/common/http';
import { ImmobileService } from '../../services/immobile.service';
import { UserService } from '../../services/user.service';
import { CarroselComponent } from '../layout/carrosel/carrosel.component';
import { LoadingComponent } from '../layout/loading/loading.component';
import { NgxMaskDirective } from 'ngx-mask';

interface cepInterface {
  nome: string
}

@Component({
  selector: 'app-create-immobile',
  standalone: true,
  imports: [
    NavbarComponent, FooterComponent, ReactiveFormsModule, FormsModule, ModalAlertComponent, CommonModule,
    ModalTextComponent, CarroselComponent, LoadingComponent, NgxMaskDirective
  ],
  templateUrl: './create-immobile.component.html',
  styleUrl: './create-immobile.component.scss'
})
export class CreateImmobileComponent implements OnInit{

  userId: string = '';
  form: FormGroup;
  formData = new FormData();
  @ViewChild('imageContainer', { static: false }) imageContainer!: ElementRef<HTMLDivElement>;
  cities: string[] = [];
  images: File[] = [];
  isLoading: boolean = false;

  //modal alert
  showModal: boolean = false;
  field: string = '';

  //modal text
  showModalText: boolean = false;
  message: string = '';
  @ViewChild(ModalTextComponent) modalComponent!: ModalTextComponent;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private cepService: CepService,
    private cdr: ChangeDetectorRef,
    private immobileService: ImmobileService,
    private userService: UserService
  ) 
  {
    this.form = this.fb.group({
      imagens: [null, Validators.required],
      titulo_do_imóvel: ['', Validators.required, Validators.maxLength(100)],
      endereço: ['', Validators.required, Validators.maxLength(50)],
      estado: ['', Validators.required, Validators.maxLength(50)],
      cidade: ['', Validators.required, Validators.maxLength(50)],
      bairro: ['', Validators.required, Validators.maxLength(50)],
      preço: ['', Validators.required],
      o_que_você_quer_fazer: ['', Validators.required],
      que_tipo_de_vendedor_você_é: ['', Validators.required],
      descrição: ['', Validators.required, Validators.maxLength(500)],
      seu_imóvel_possui_garagem: ['', Validators.required],
      quantidade_de_comodos: ['', Validators.required],
      quantidade_de_banheiros: ['', Validators.required],
      integridade_do_imóvel: ['', Validators.required],
      quantidade_de_quartos_do_imóvel: ['', Validators.required],
      valor_do_IPTU: [''],
      seu_imóvel_possui_suite: ['', Validators.required],
      qual_a_idade_do_seu_imóvel: ['', Validators.required],
      tipo_do_imóvel: ['', Validators.required],
      qual_a_area_total: [''],
      possui_jardim: ['', Validators.required],
      gatedCommunity: [false],
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
      seaView: [false]
    })
  }

  ngOnInit(): void {
    const id = this.userService.getIdOfTheUserLogged();
    if (id) {
      this.userId = id;
    }

    this.form.get('titulo_do_imóvel')?.valueChanges.subscribe(value => {
      this.verifyLength(value.length, 'nome do imóvel', 100);
    })
  }

  submit():void {
    this.isLoading = true;
    this.populateFormData();
    this.immobileService.create(this.formData).subscribe({
      next: (response: HttpResponse<any>) => {
        this.isLoading = false;
        this.activeModalText('Imóvel cadastrado com sucesso!');
        this.waitForModalClose().then(() => {
          this.router.navigate(["/"]);
        })
      },
      error: (error) => {
        this.isLoading = false;
        this.activeModalText('Ocorreu um erro interno, por favor tente mais tarde!');
        this.waitForModalClose().then(() => {
          // this.router.navigate(["/"]);
        })
      }
    })
  }

  verifyFields() {
    const invalidFields = this.getInvalidFields();
    
    if (invalidFields[0]) {
      this.showModal = false;
      setTimeout(() => {
        this.field = this.field = invalidFields[0];
        this.showModal = true;
        this.cdr.detectChanges();
      });
    } else {
      this.submit();
    }
  }

  getInvalidFields(): string[] {
    const invalidFields: string[] = [];

    Object.keys(this.form.controls).forEach(key => {
      const control = this.form.get(key);
      if (control && control.invalid) {
        invalidFields.push(key);
      }
    });

    return invalidFields;
  }

  onFileChange(event: any):void {
    const files = event.target.files as FileList;
    if (files) {
      this.images = this.images.concat(Array.from(files));
    }
  }

  findCitiesByState(event: Event) {
    const selectedState = this.form.get('estado')?.value;
    this.cities = [];
    
    this.cepService.findCities(selectedState).subscribe({
      next: (response: HttpResponse<any>) => {
        if (response.body && Array.isArray(response.body)) {
          this.cities = response.body.map((city: cepInterface) => city.nome)
            .sort((a, b) => a.localeCompare(b));
        }
      },
      error: (error) => {
        this.activeModalText("Aconteceu um erro interno, Por favor tente mais tarde.");
        this.waitForModalClose().then(() => {
          this.router.navigate(["/"]);
        })
        console.log('Erro ao buscar cidades por UF: ', error);
      }
    });
  }

  searchCity(event: Event) {
    if (!this.form.get('estado')?.valid) {
      this.activeModalText("Selecione o seu estado para continuar!");
      this.form.patchValue({
        cidade: ''
      })
    }
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

  populateFormData():void {

    const booleanFields = [
      'gatedCommunity', 'videos', 'beach', 'disabledAccess', 'playground', 'grill',
      'energyGenerator', 'closeToTheCenter', 'elevator', 'pool', 'frontDesk',
      'multiSportsCourt', 'gym', 'steamRoom', 'cableTV', 'heating', 'cabinetsInTheKitchen',
      'bathroomInTheRoom', 'internet', 'partyRoom', 'airConditioning', 'americanKitchen',
      'hydromassage', 'fireplace', 'privatePool', 'electronicGate', 'serviceArea', 'pub',
      'closet', 'office', 'yard', 'alarmSystem', 'balcony', 'concierge24Hour', 'walledArea',
      'dogAllowed', 'catAllowed', 'cameras', 'furnished', 'seaView'
   ];

   booleanFields.forEach(field => {
      this.formData.set(field, this.form.get(field)?.value ? 'true' : 'false');
   });

    this.formData.set('studentId', this.userId);
    this.formData.set('name', this.form.get('titulo_do_imóvel')?.value);
    this.formData.set('address', this.form.get('endereço')?.value);
    this.formData.set('state', this.form.get('estado')?.value);
    this.formData.set('city', this.form.get('cidade')?.value);
    this.formData.set('neighborhood', this.form.get('bairro')?.value);
    this.formData.set('category', this.form.get('o_que_você_quer_fazer')?.value);
    this.formData.set('sellerType', this.form.get('que_tipo_de_vendedor_você_é')?.value);
    this.formData.set('description', this.form.get('descrição')?.value);
    this.formData.set('garage', this.form.get('seu_imóvel_possui_garagem')?.value);
    this.formData.set('quantityRooms', this.form.get('quantidade_de_comodos')?.value);
    this.formData.set('quantityBathrooms', this.form.get('quantidade_de_banheiros')?.value);
    this.formData.set('integrity', this.form.get('integridade_do_imóvel')?.value);
    this.formData.set('quantityBedrooms', this.form.get('quantidade_de_quartos_do_imóvel')?.value);
    this.formData.set('suite', this.form.get('seu_imóvel_possui_suite')?.value);
    this.formData.set('age', this.form.get('qual_a_idade_do_seu_imóvel')?.value);
    this.formData.set('type', this.form.get('tipo_do_imóvel')?.value);
    this.formData.set('totalArea', this.form.get('qual_a_area_total')?.value);
    this.formData.set('garden', this.form.get('possui_jardim')?.value);
    this.formData.set('IPTU', this.form.get('valor_do_IPTU')?.value);
    this.formData.set('price', this.form.get('preço')?.value);
    
    this.images.forEach((image, index) => {
      this.formData.append(`files`, image);
    });
  }

  verifyLength(lengthField: number, field: string, quantityVerify: number): void {

    if (quantityVerify === 50) {
      if (lengthField >= 50) {
        this.activeModalText(`O campo ${field} não pode ter mais de 50 caracteres!`);
      }
    }
    if (quantityVerify === 100) {
      if (lengthField >= 100) {
        this.activeModalText(`O campo ${field} não pode ter mais de 100 caracteres!`);
      }
    }
    if (quantityVerify === 500) {
      if (lengthField >= 500) {
        this.activeModalText(`O campo ${field} não pode ter mais de 500 caracteres!`);
      }
    }
  }
}
