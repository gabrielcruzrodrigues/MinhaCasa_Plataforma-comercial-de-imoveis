import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-modal-text',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './modal-text.component.html',
  styleUrl: './modal-text.component.scss'
})
export class ModalTextComponent {
  @Input() showModal: boolean = false;
  @Output() onClose = new EventEmitter<void>();
  _message: string = '';

  @Input() set message(value: string) {
    this._message = value;
    this.cdr.detectChanges();
  }

  constructor(private cdr: ChangeDetectorRef) {}

  closeModal() {
    this.showModal = false;
    this.cdr.detectChanges();
    this.onClose.emit();
  }
}
