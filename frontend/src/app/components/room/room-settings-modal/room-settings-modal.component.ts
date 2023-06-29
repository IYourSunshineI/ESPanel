import {Component} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Room} from "../../../dtos/room";

@Component({
  selector: 'app-room-settings-modal',
  templateUrl: './room-settings-modal.component.html',
  styleUrls: ['./room-settings-modal.component.scss']
})
export class RoomSettingsModalComponent {

  room: Room;

  constructor(
    public activeModal: NgbActiveModal,
  ) { }

  save() {
    //TODO
    console.log('save', this.room);
  }

  delete() {
    //TODO
    console.log('delete', this.room);
  }
}
