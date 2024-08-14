import { ChangeDetectorRef, Component, ElementRef, ViewChild } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { FooterComponent } from '../layout/footer/footer.component';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CarroselComponent } from '../layout/carrosel/carrosel.component';
import { AuthService } from '../../services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { ImmobileService } from '../../services/immobile.service';
import { ModalAlertComponent } from '../layout/modal-alert/modal-alert.component';
import { ModalTextComponent } from '../layout/modal-text/modal-text.component';
import { LoadingComponent } from '../layout/loading/loading.component';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { ConverterFieldName } from '../../utils/ConverterFieldNameToPortuguese';
import { CepService } from '../../services/cep.service';
import { CommonModule } from '@angular/common';
import { url } from 'inspector';

interface cepInterface {
  nome: string
}

@Component({
  selector: 'app-update-immobile',
  standalone: true,
  imports: [
    NavbarComponent, FooterComponent, CarroselComponent, ReactiveFormsModule, ModalAlertComponent, ModalTextComponent, LoadingComponent,
    CommonModule
  ],
  templateUrl: './update-immobile.component.html',
  styleUrl: './update-immobile.component.scss'
})
export class UpdateImmobileComponent {
  userId: string = '';
  immobileId: string | null = '';

  formData = new FormData();
  form: FormGroup;
  images: File[] = [];
  @ViewChild('imageContainer', { static: false }) imageContainer!: ElementRef<HTMLDivElement>;
  cities: string[] = [];

  isLoading: boolean = true;

  //modal alert
  showModal: boolean = false;
  field: string = '';

  //modal text
  showModalText: boolean = false;
  message: string = '';
  @ViewChild(ModalTextComponent) modalComponent!: ModalTextComponent;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private userService: UserService,
    private immobileService: ImmobileService,
    private cdr: ChangeDetectorRef,
    private cepService: CepService,
    private route: ActivatedRoute,
    private http: HttpClient
  ) {
    this.form = this.fb.group({
      immobileId: [''],
      files: [null],
      immobileTitle: ['', Validators.required],
      description: ['', Validators.required],
      address: ['', Validators.required],
      state: ['', Validators.required],
      city: ['', Validators.required],
      neighborhood: ['', Validators.required],
      price: ['', Validators.required],
      category: ['', Validators.required],
      sellerType: ['', Validators.required],
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

    this.immobileId = this.route.snapshot.paramMap.get('id');
    this.route.paramMap.subscribe(param => {
      this.immobileId = param.get('id');
    });

    this.getInitialImmobileInfos();
  }

  getInitialImmobileInfos(): void {
    this.immobileService.findById(this.immobileId).subscribe({
      next: (response: HttpResponse<any>) => {
        if (response.status === 200) {
          this.populateInitialImmobileData(response.body);
        } else {
          this.isLoading = false;
          this.activeModalText("Ocorreu um erro interno, tente novamente mais tarde!");
        }
      },
      error: (error) => {
        console.log("Erro ao buscar os dados do imóvel.");
        // console.log(error);
        this.activeModalText("Ocorreu um erro interno, tente novamente mais tarde!");
        this.isLoading = false;
        this.waitForModalClose().then(() => {
          this.router.navigate(["/"]);
        })
      }
    })
  }

  populateInitialImmobileData(body: any): void {
    this.downloadImages(body.files.reverse());
    this.cities.push(body.city);

    this.form.patchValue({
      immobileId: body.id,
      // files: body.files,
      immobileTitle: body.name,
      description: body.description,
      address: body.address,
      city: body.city,
      state: body.state,
      neighborhood: body.neighborhood,
      price: body.price,
      category: body.category,
      sellerType: body.sellerType,
      garage: body.garage,
      quantityRooms: body.quantityRooms,
      quantityBathrooms: body.quantityBathrooms,
      integrity: body.integrity,
      quantityBedrooms: body.quantityBedrooms,
      IPTU: body.iptu,
      suite: body.suite,
      age: body.age,
      type: body.type,
      totalArea: body.totalArea,
      garden: body.garden,
      gatedCommunity: body.gatedCommunity,
      beach: body.beach,
      disabledAccess: body.disabledAccess,
      playground: body.playground,
      grill: body.grill,
      energyGenerator: body.energyGenerator,
      closeToTheCenter: body.closeToTheCenter,
      elevator: body.elevator,
      pool: body.pool,
      frontDesk: body.frontDesk,
      multiSportsCourt: body.multiSportsCourt,
      gym: body.gym,
      steamRoom: body.steamRoom,
      cableTV: body.cableTV,
      heating: body.heating,
      cabinetsInTheKitchen: body.cabinetsInTheKitchen,
      bathroomInTheRoom: body.bathroomInTheRoom,
      internet: body.internet,
      partyRoom: body.partyRoom,
      airConditioning: body.airConditioning,
      americanKitchen: body.americanKitchen,
      hydromassage: body.hydromassage,
      fireplace: body.fireplace,
      privatePool: body.privatePool,
      electronicGate: body.electronicGate,
      serviceArea: body.serviceArea,
      pub: body.pub,
      closet: body.closet,
      office: body.office,
      yard: body.yard,
      alarmSystem: body.alarmSystem,
      balcony: body.balcony,
      concierge24Hour: body.concierge24Hour,
      walledArea: body.walledArea,
      dogAllowed: body.dogAllowed,
      catAllowed: body.catAllowed,
      cameras: body.cameras,
      furnished: body.furnished,
      seaView: body.seaView,
      active: body.active,
    })
    this.isLoading = false;
  }

  downloadImages(urls: string[]): void {
    urls.forEach(url => {
      this.http.get(url, { responseType: 'blob' }).subscribe(blob => {
        const file = new File([blob], this.getFileNameFromUrl(url), {type: blob.type });
        this.images = this.images.concat(file);
      });
    });
    console.log(this.images);
  }

  getFileNameFromUrl(url: string): string {
    return url.split('/').pop() || 'downloaded-image';
  }

  submit():void {
    this.isLoading = true;
    this.populateFormData();
    this.immobileService.update(this.formData).subscribe({
      next: (response: HttpResponse<any>) => {
        this.isLoading = false;
        this.activeModalText('Imóvel atualizado com sucesso!');
        this.waitForModalClose().then(() => {
          this.router.navigate(["/"]);
        })
      },
      error: (error) => {
        this.isLoading = false;
        this.activeModalText('Ocorreu um erro interno, por favor tente mais tarde!');
        this.waitForModalClose().then(() => {
          // this.router.navigate(["/"]);
          console.log(error)
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
      'gatedCommunity', 'beach', 'disabledAccess', 'playground', 'grill',
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

    this.formData.set('immobileId', this.form.get('immobileId')?.value);
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
}
