import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { ArrowCarroselComponent } from '../layout/arrow-carrosel/arrow-carrosel.component';
import { ImmobileService } from '../../services/immobile.service';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';

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

  name: string = '';
  address: string = '';
  state: string = '';
  city: string = '';
  price: string = '';
  category: string = '';

  constructor(private immobileService: ImmobileService, private route: ActivatedRoute){}

  ngOnInit(): void {
    this.immobileId = this.route.snapshot.paramMap.get('id');

    this.immobileService.getImmobileWithCompleteImagesPath(this.immobileId).subscribe({
      next: (response: HttpResponse<any>) => {
        this.populateFields(response.body);
      }
    })
  }
  
  populateFields(body: any): void {
    this.imagesUrl = body.files;
    this.name = body.name;
    this.address = body.address;
    this.state = body.state;
    this.city = body.city;
    this.price = body.price;

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
}
