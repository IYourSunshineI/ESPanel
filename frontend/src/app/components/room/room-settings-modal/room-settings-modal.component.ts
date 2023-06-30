import {AfterContentInit, Component} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Room} from "../../../dtos/room";
import {RoomService} from "../../../services/room.service";

@Component({
  selector: 'app-room-settings-modal',
  templateUrl: './room-settings-modal.component.html',
  styleUrls: ['./room-settings-modal.component.scss']
})
export class RoomSettingsModalComponent implements AfterContentInit{

  room: Room;
  updatedRoom: Room;
  error: boolean = false;
  errorMessage: string = '';

  ngAfterContentInit(): void {
    this.updatedRoom = {...this.room};
  }
  constructor(
    public activeModal: NgbActiveModal,
    private service: RoomService,
  ) { }

  save() {
    this.service.update(this.updatedRoom).subscribe({
      next: data => {
        this.updatedRoom = data;
        console.log('updated room: ', data);
        this.activeModal.dismiss('updated');
      },
      error: e => {
        console.error('error updating room: ', e);
        this.error = true;
        this.errorMessage = e.error[0].defaultMessage;
      }
    });
  }

  delete() {
    if(!this.room.id){
      return;
    }
    this.service.delete(this.room.id).subscribe({
      next: data => {
        console.log('deleted room: ', data);
        this.activeModal.dismiss('deleted');
      },
      error: e => {
        console.error('error deleting room: ', e);
      }
    });
  }
}
