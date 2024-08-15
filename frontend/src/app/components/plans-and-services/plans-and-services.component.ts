import { Component } from '@angular/core';
import { FooterComponent } from '../layout/footer/footer.component';
import { NavbarComponent } from '../layout/navbar/navbar.component';

@Component({
  selector: 'app-plans-and-services',
  standalone: true,
  imports: [
    FooterComponent, NavbarComponent
  ],
  templateUrl: './plans-and-services.component.html',
  styleUrl: './plans-and-services.component.scss'
})
export class PlansAndServicesComponent {

}
