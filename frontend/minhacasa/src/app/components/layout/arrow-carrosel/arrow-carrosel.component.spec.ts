import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArrowCarroselComponent } from './arrow-carrosel.component';

describe('ArrowCarroselComponent', () => {
  let component: ArrowCarroselComponent;
  let fixture: ComponentFixture<ArrowCarroselComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArrowCarroselComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ArrowCarroselComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
