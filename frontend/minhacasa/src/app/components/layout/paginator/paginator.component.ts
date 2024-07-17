import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-paginator',
  standalone: true,
  imports: [],
  templateUrl: './paginator.component.html',
  styleUrl: './paginator.component.scss'
})
export class PaginatorComponent {
  @Input() length: number = 0;
  @Input() pageSize: number = 10;
  pageIndex: number = 1;

  @Output() previousPage = new EventEmitter<number>();
  @Output() nextPage = new EventEmitter<number>();

  get totalPages(): number {
    return Math.ceil(this.length / this.pageSize);
  }

  goToPreviousPage() {
    if (this.pageIndex > 0) {
      this.pageIndex--;
      this.previousPage.emit(this.pageIndex);
    }
  }

  goToNextPage() {
    if (this.pageIndex) {
      this.pageIndex++;
      this.nextPage.emit(this.pageIndex);
    }
  }
}
