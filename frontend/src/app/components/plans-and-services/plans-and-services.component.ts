import { Component } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { FooterComponent } from '../layout/footer/footer.component';

@Component({
  selector: 'app-plans-and-services',
  standalone: true,
  imports: [
    NavbarComponent, FooterComponent
  ],
  templateUrl: './plans-and-services.component.html',
  styleUrl: './plans-and-services.component.scss'
})
export class PlansAndServicesComponent {

}
