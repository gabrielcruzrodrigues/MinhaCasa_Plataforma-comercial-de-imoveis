import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateImmobileComponent } from './update-immobile.component';

describe('UpdateImmobileComponent', () => {
  let component: UpdateImmobileComponent;
  let fixture: ComponentFixture<UpdateImmobileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateImmobileComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateImmobileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
