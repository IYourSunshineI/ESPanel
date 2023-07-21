import {AfterContentInit, Component} from '@angular/core';
import {Room} from "../../dtos/room";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {RoomService} from "../../services/room.service";

@Component({
  selector: 'app-settings-modal',
  templateUrl: './settings-modal.component.html',
  styleUrls: ['./settings-modal.component.scss']
})
export class SettingsModalComponent implements AfterContentInit {

  room: Room;
  updatedRoom: Room;

  constructor(
    public activeModal: NgbActiveModal,
    private service: RoomService,
  ) {
  }

  ngAfterContentInit(): void {
    this.updatedRoom = {...this.room};
  }

  updateRoom($event: Room) {
    this.room = $event;
    this.activeModal.dismiss('updated');
  }

}
