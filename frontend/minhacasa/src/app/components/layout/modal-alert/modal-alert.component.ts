import { CommonModule } from '@angular/common';
import { Component, Input, OnInit, ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-modal-alert',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './modal-alert.component.html',
  styleUrl: './modal-alert.component.scss'
})
export class ModalAlertComponent implements OnInit{
  @Input() showModal: boolean = false;
  @Input() field: string = '';
  message: string = '';

  constructor(private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.message = `Preencha o campo [ ${this.field} ] para continuar!`
  }

  closeModal() {
    this.showModal = false;
    this.cdr.detectChanges();
  }
}
