import { TestBed } from '@angular/core/testing';

import { RgbModuleService } from './rgb-module.service';

describe('RgbModuleService', () => {
  let service: RgbModuleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RgbModuleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
