import { Component } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { CardComponent } from '../layout/card/card.component';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [NavbarComponent, CardComponent],
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss'
})
export class SearchComponent {

}
