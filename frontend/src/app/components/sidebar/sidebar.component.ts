import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {RoomCreateModalComponent} from "../room/room-create-modal/room-create-modal.component";
import {Room} from "../../dtos/room";
import {RoomService} from "../../services/room.service";


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit{

  @Output()
  roomChanged: EventEmitter<Room> = new EventEmitter<Room>();

  lastRoom: Room;
  rooms: Room[];
  constructor(
    private modalService: NgbModal,
    private service: RoomService,
  ) { }

  ngOnInit() {
    this.loadRooms();
  }

  async openCreateRoomModal() {
    const modalRef = this.modalService.open(RoomCreateModalComponent,
      {centered: true});
    try {
      await modalRef.result;
    } catch (dismissReason) {
      console.log('dismissed reason: ', dismissReason);
      this.loadRooms();
    }
  }

  changeRoom(room: Room) {
    this.lastRoom = room;
    this.roomChanged.emit(room);
  }

  loadRooms() {
    this.service.getAll().subscribe( {
      next: data => {
        this.rooms = data;
        const temp = this.rooms.find(r => r.id === this.lastRoom.id);
        this.changeRoom(temp ? temp : this.rooms[0]);
      },
      error: e => {
        console.error('error loading rooms: ', e);
      }
    });
  }
}
