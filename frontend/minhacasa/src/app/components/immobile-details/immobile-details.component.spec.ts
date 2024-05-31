import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImmobileDetailsComponent } from './immobile-details.component';

describe('ImmobileDetailsComponent', () => {
  let component: ImmobileDetailsComponent;
  let fixture: ComponentFixture<ImmobileDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ImmobileDetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ImmobileDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
