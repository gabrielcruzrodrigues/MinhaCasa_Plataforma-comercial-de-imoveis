import { Component } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';

@Component({
  selector: 'app-immobile-details',
  standalone: true,
  imports: [NavbarComponent],
  templateUrl: './immobile-details.component.html',
  styleUrl: './immobile-details.component.scss'
})
export class ImmobileDetailsComponent {

}
