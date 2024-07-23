import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalTextComponent } from './modal-text.component';

describe('ModalTextComponent', () => {
  let component: ModalTextComponent;
  let fixture: ComponentFixture<ModalTextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalTextComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModalTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
