import { TestBed } from '@angular/core/testing';

import { DimmerModuleService } from './dimmer-module.service';

describe('DimmerModuleService', () => {
  let service: DimmerModuleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DimmerModuleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
