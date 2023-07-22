import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Room} from "../../../dtos/room";
import {RoomService} from "../../../services/room.service";

@Component({
  selector: 'app-room-settings',
  templateUrl: './room-settings.component.html',
  styleUrls: ['./room-settings.component.scss']
})
export class RoomSettingsComponent {

  @Input() room: Room;
  @Output() updatedRoom: EventEmitter<Room> = new EventEmitter<Room>();
  @Output() dismissReason: EventEmitter<string> = new EventEmitter<string>();
  error: boolean = false;
  errorMessage: string = '';

  constructor(
    private service: RoomService,
  ) { }

  cancel() {
    this.dismissReason.emit('cancel');
  }

  save() {
    this.service.update(this.room).subscribe({
      next: data => {
        this.room = data;
        console.log('updated room: ', data);
        this.updatedRoom.emit(data);
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
        this.dismissReason.emit('deleted');
      },
      error: e => {
        console.error('error deleting room: ', e);
      }
    });
  }
}
