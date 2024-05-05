import { Component } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { CardComponent } from '../layout/card/card.component';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [
    NavbarComponent, CardComponent
  ],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent {

}
