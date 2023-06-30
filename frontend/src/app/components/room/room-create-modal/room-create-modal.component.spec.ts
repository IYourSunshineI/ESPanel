import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomCreateModalComponent } from './room-create-modal.component';

describe('RoomCreateModalComponent', () => {
  let component: RoomCreateModalComponent;
  let fixture: ComponentFixture<RoomCreateModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RoomCreateModalComponent]
    });
    fixture = TestBed.createComponent(RoomCreateModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
