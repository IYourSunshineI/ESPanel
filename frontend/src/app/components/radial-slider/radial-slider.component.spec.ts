import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RadialSliderComponent } from './radial-slider.component';

describe('RadialSliderComponent', () => {
  let component: RadialSliderComponent;
  let fixture: ComponentFixture<RadialSliderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RadialSliderComponent]
    });
    fixture = TestBed.createComponent(RadialSliderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
