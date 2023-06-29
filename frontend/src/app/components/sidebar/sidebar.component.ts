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
    this.roomChanged.emit(room);
  }

  private loadRooms() {
    this.service.getAll().subscribe( {
      next: data => {
        this.rooms = data;
      },
      error: e => {
        console.error('error loading rooms: ', e);
      }
    });
  }
}
