import { Component } from '@angular/core';
import {Room} from "./dtos/room";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {RoomSettingsModalComponent} from "./components/room/room-settings-modal/room-settings-modal.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';

  defaultRoomName = 'select a room';
  room: Room = {
    title: this.defaultRoomName,
  };

  constructor(
    private modalService: NgbModal,
  ) {
  }

  roomChanged(room: Room) {
    this.room = room;
  }

  async openRoomSettings() {
    if(this.room.title === this.defaultRoomName) {
      return;
    }
    const modalRef = this.modalService.open(RoomSettingsModalComponent,
      {centered: true});
    modalRef.componentInstance.room = this.room;
  }
}
