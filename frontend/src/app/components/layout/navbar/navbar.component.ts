import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent implements OnInit{
  isLogged: boolean = false;
  noLogged: boolean = false;
  isOpen = false;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    if (!this.authService.verifyIfAreLoggedIn()) {
      this.noLogged = true;
    } else {
      this.isLogged = true;
    }
  }

  openDropdown(): void {
    this.isOpen = true;
  }
  
  closeDropdown(): void {
    this.isOpen = false;
  }
}
