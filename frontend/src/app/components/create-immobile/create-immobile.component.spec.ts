import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateImmobileComponent } from './create-immobile.component';

describe('CreateImmobileComponent', () => {
  let component: CreateImmobileComponent;
  let fixture: ComponentFixture<CreateImmobileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateImmobileComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateImmobileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
