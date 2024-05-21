import { Component } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { FooterComponent } from '../layout/footer/footer.component';

@Component({
  selector: 'app-create-immobile',
  standalone: true,
  imports: [NavbarComponent, FooterComponent],
  templateUrl: './create-immobile.component.html',
  styleUrl: './create-immobile.component.scss'
})
export class CreateImmobileComponent {

}
