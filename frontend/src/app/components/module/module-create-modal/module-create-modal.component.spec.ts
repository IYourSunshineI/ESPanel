import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModuleCreateModalComponent } from './module-create-modal.component';

describe('ModuleCreateModalComponent', () => {
  let component: ModuleCreateModalComponent;
  let fixture: ComponentFixture<ModuleCreateModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModuleCreateModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModuleCreateModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
