import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarroselComponent } from './carrosel.component';

describe('CarroselComponent', () => {
  let component: CarroselComponent;
  let fixture: ComponentFixture<CarroselComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarroselComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CarroselComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
