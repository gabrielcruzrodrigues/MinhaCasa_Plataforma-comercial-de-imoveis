import { CommonModule } from '@angular/common';
import { Component, Input, OnInit, ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-modal-alert',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './modal-alert.component.html',
  styleUrl: './modal-alert.component.scss'
})
export class ModalAlertComponent {
  @Input() showModal: boolean = false;
  private _field: string = '';
  message: string = '';

  @Input() set field(value: string) {
    value = value.replace(/_/g, ' ');
    this._field = value;
    this.message = `Preencha o campo [ ${value} ] para continuar!`;
    this.cdr.detectChanges();
  }

  constructor(private cdr: ChangeDetectorRef) {}

  closeModal() {
    this.showModal = false;
    this.cdr.detectChanges();
  }
}
