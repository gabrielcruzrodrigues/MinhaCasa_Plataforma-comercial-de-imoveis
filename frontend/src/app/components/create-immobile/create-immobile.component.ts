import { ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
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
import { ConverterFieldName } from '../../utils/ConverterFieldNameToPortuguese';
import { AuthService } from '../../services/auth.service';
import { NavbarComponent } from "../layout/navbar/navbar.component";

interface cepInterface {
  nome: string
}

@Component({
  selector: 'app-create-immobile',
  standalone: true,
  imports: [
    FooterComponent, ReactiveFormsModule, FormsModule, ModalAlertComponent, CommonModule,
    ModalTextComponent, CarroselComponent, LoadingComponent, NgxMaskDirective,
    NavbarComponent
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
    private userService: UserService,
    private authService: AuthService
  ) 
  {
    this.form = this.fb.group({
      files: [null, Validators.required],
      immobileTitle: ['', Validators.required],
      address: ['', Validators.required],
      state: ['', Validators.required],
      city: ['', Validators.required],
      neighborhood: ['', Validators.required],
      price: ['', Validators.required],
      category: ['', Validators.required],
      sellerType: ['', Validators.required],
      description: ['', Validators.required],
      garage: ['', Validators.required],
      quantityRooms: ['', Validators.required],
      quantityBathrooms: ['', Validators.required],
      integrity: ['', Validators.required],
      quantityBedrooms: ['', Validators.required],
      IPTU: [''],
      suite: ['', Validators.required],
      age: ['', Validators.required],
      type: ['', Validators.required],
      totalArea: [''],
      garden: ['', Validators.required],
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
    if (!this.authService.verifyIfAreLoggedIn()) {
      this.router.navigate(['/login']);
    } else {
      const id = this.authService.getUserId();
      this.form.patchValue({
        senderId: id
      })
    }

    const id = this.userService.getIdOfTheUserLogged();
    if (id) {
      this.userId = id;
    }
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
        this.field = ConverterFieldName.verify(invalidFields[0]);
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
    const maxSize = 5 * 1024 * 1024;
    const validFiles: File[] = [];

    if (files) {
      for (let i = 0; i < files.length; i++) {
        const file = files[i];
        if (file.size <= maxSize) {
          validFiles.push(file);
        } else {
          this.activeModalText(`O arquivo ${file.name} é maior que 5MB e não será adicionado.`);
        }
      }

      this.images = this.images.concat(validFiles);
    }
  }

  findCitiesByState(event: Event) {
    const selectedState = this.form.get('state')?.value;
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
    if (!this.form.get('state')?.valid) {
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
    this.formData.set('immobileTitle', this.form.get('immobileTitle')?.value);
    this.formData.set('address', this.form.get('address')?.value);
    this.formData.set('state', this.form.get('state')?.value);
    this.formData.set('city', this.form.get('city')?.value);
    this.formData.set('neighborhood', this.form.get('neighborhood')?.value);
    this.formData.set('category', this.form.get('category')?.value);
    this.formData.set('sellerType', this.form.get('sellerType')?.value);
    this.formData.set('description', this.form.get('description')?.value);
    this.formData.set('garage', this.form.get('garage')?.value);
    this.formData.set('quantityRooms', this.form.get('quantityRooms')?.value);
    this.formData.set('quantityBathrooms', this.form.get('quantityBathrooms')?.value);
    this.formData.set('integrity', this.form.get('integrity')?.value);
    this.formData.set('quantityBedrooms', this.form.get('quantityBedrooms')?.value);
    this.formData.set('suite', this.form.get('suite')?.value);
    this.formData.set('age', this.form.get('age')?.value);
    this.formData.set('type', this.form.get('type')?.value);
    this.formData.set('totalArea', this.form.get('totalArea')?.value);
    this.formData.set('garden', this.form.get('garden')?.value);
    this.formData.set('IPTU', this.form.get('IPTU')?.value);
    this.formData.set('price', this.form.get('price')?.value);
    
    this.images.forEach((image, index) => {
      this.formData.append(`files`, image);
    });
  }

  // verifyLength(lengthField: number, field: string, quantityVerify: number): void {

  //   if (quantityVerify === 50) {
  //     if (lengthField >= 50) {
  //       this.activeModalText(`O campo ${field} não pode ter mais de 50 caracteres!`);
  //     }
  //   }
  //   if (quantityVerify === 100) {
  //     if (lengthField >= 100) {
  //       this.activeModalText(`O campo ${field} não pode ter mais de 100 caracteres!`);
  //     }
  //   }
  //   if (quantityVerify === 500) {
  //     if (lengthField >= 500) {
  //       this.activeModalText(`O campo ${field} não pode ter mais de 500 caracteres!`);
  //     }
  //   }
  // }
}
