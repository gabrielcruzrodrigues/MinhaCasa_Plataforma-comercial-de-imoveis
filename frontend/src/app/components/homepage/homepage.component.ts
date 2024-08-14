import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { NavbarComponent } from '../layout/navbar/navbar.component';
import { CardComponent } from '../layout/card/card.component';
import { FooterComponent } from '../layout/footer/footer.component';
import { ImmobileService } from '../../services/immobile.service';
import { HttpResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { ModalTextComponent } from '../layout/modal-text/modal-text.component';

interface cardInterface {
  id: string,
  quantityRooms: string,
  quantityBedrooms: string,
  quantityBathrooms: string,
  imageUrl: string,
  price: string,
  name: string,
  sellerId: string
}

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [
    NavbarComponent, CardComponent, FooterComponent, CommonModule, ModalTextComponent
  ],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent implements OnInit{
  immobiles: cardInterface[] = [];
  showModalText: boolean = false;
  message: string = '';
  @ViewChild(ModalTextComponent) modalComponent!: ModalTextComponent;

  constructor (
    private immobileService: ImmobileService, 
    private authService: AuthService, 
    private router: Router, 
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    if (this.authService.verifyIfAreLoggedIn()) {
      console.log('passou')
      this.authService.isLogged().subscribe({
        next: (response: HttpResponse<any>) => {
          if (response.status === 204) {
            //user logged
          }
        },
        error: () => {
          //user not logged
          this.authService.clearLocalStorage();
          this.activeModalText("Faça o login novamente para acessar a sua conta!");
          this.waitForModalClose().then(() => {
            this.router.navigate(["/login"]);
          });
        }
      });
      return;
    }

    this.immobileService.find4immobilesForHome().subscribe({
      next: (response: HttpResponse<any>) => {
        this.immobiles = response.body;
      },
      error: (error) => {
        console.log("Ocorreu um erro ao tentar buscar os cards");
      }
    })
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
  

}
