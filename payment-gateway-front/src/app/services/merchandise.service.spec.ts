import { TestBed, inject } from '@angular/core/testing';

import { MerchandiseService } from './merchandise.service';

describe('MerchandiseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MerchandiseService]
    });
  });

  it('should be created', inject([MerchandiseService], (service: MerchandiseService) => {
    expect(service).toBeTruthy();
  }));
});
