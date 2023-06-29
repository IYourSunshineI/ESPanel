import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomSettingsModalComponent } from './room-settings-modal.component';

describe('RoomSettingsModalComponent', () => {
  let component: RoomSettingsModalComponent;
  let fixture: ComponentFixture<RoomSettingsModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RoomSettingsModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoomSettingsModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
