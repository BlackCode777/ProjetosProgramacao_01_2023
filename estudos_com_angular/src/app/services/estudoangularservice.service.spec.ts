import { TestBed } from '@angular/core/testing';

import { EstudoangularserviceService } from './estudoangularservice.service';

describe('EstudoangularserviceService', () => {
  let service: EstudoangularserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EstudoangularserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
