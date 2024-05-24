import { TestBed } from '@angular/core/testing';

import { ImmobileService } from './immobile.service';

describe('ImmobileService', () => {
  let service: ImmobileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImmobileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
