import { TestBed } from '@angular/core/testing';

import { KnobModuleService } from './knob-module.service';

describe('KnobModuleService', () => {
  let service: KnobModuleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KnobModuleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
