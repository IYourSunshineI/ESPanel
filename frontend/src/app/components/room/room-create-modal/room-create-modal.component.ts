import { Component } from '@angular/core';
import {RoomService} from "../../../services/room.service";
import {Room} from "../../../dtos/room";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-room-create-modal',
  templateUrl: './room-create-modal.component.html',
  styleUrls: ['./room-create-modal.component.scss']
})
export class RoomCreateModalComponent {

  room: Room = {
    title: '',
  }

  error: boolean = false;
  errorMessage: string = '';

  constructor(
    private service: RoomService,
    public activeModal: NgbActiveModal,
  ) { }


  createRoom() {
    this.service.create(this.room).subscribe( {
      next: data => {
        console.log('created room: ', data);
        this.activeModal.dismiss('created');
      },
      error: e => {
        console.error('error creating room: ', e.error[0].defaultMessage);
        this.error = true;
        this.errorMessage = e.error[0].defaultMessage;
      }
    });
  }
}
