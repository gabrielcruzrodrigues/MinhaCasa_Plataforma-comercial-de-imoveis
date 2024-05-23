import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { FooterComponent } from '../layout/footer/footer.component';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ModalAlertComponent } from '../layout/modal-alert/modal-alert.component';
import { ModalTextComponent } from '../layout/modal-text/modal-text.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-immobile',
  standalone: true,
  imports: [
    NavbarComponent, FooterComponent, ReactiveFormsModule, FormsModule, ModalAlertComponent, CommonModule, ModalTextComponent,
  ],
  templateUrl: './create-immobile.component.html',
  styleUrl: './create-immobile.component.scss'
})
export class CreateImmobileComponent implements OnInit{

  form: FormGroup;
  @ViewChild('imageContainer', { static: false }) imageContainer!: ElementRef<HTMLDivElement>;

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) 
  {
    this.form = this.fb.group({
      imagens: [null, Validators.required],
      titulo_do_imóvel: ['', Validators.required],
      endereço: ['', Validators.required],
      estado: ['', Validators.required],
      cidade: ['', Validators.required],
      bairro: ['', Validators.required],
      preço: ['', Validators.required],
      o_que_você_quer_fazer: ['', Validators.required],
      que_tipo_de_vendedor_você_é: ['', Validators.required],
      descrição: ['', Validators.required],
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
    
  }

  submit():void {
    console.log(this.form.value);
  }

  onFileChange(event: any):void {
    const files = event.target.files as FileList;
    const container = this.imageContainer.nativeElement;
    container.innerHTML = ''; //limpa a div antes de adicionar novas imagens

    Array.from(files).forEach((file: File) => {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const img = document.createElement('img');
        img.src = e.target.result;
        img.style.width = '80%'; // Define o tamanho das imagens
        img.style.height = '80%'; // Define o tamanho das imagens
        img.style.margin = '0px'; // Adiciona margem entre as imagens
        img.style.objectFit = 'contain'
        container.appendChild(img);
      };
      reader.readAsDataURL(file);
    });

  }
}
