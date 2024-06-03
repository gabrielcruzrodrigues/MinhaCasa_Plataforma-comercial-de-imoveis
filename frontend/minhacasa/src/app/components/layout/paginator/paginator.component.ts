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
  @Input() pageIndex: number = 0;

  @Output() page: EventEmitter<number> = new EventEmitter<number>();

  get totalPages(): number {
    return Math.ceil(this.length / this.pageSize);
  }

  goToFirstPage() {
    this.pageIndex = 0;
    this.page.emit(this.pageIndex);
  }

  goToPreviousPage() {
    if (this.pageIndex > 0) {
      this.pageIndex--;
      this.page.emit(this.pageIndex);
    }
  }

  goToNextPage() {
    if (this.pageIndex < this.totalPages - 1) {
      this.pageIndex++;
      this.page.emit(this.pageIndex);
    }
  }

  goToLastPage() {
    this.pageIndex = this.totalPages - 1;
    this.page.emit(this.pageIndex);
  }
}
