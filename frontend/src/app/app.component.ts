import {Component, ViewChild} from '@angular/core';
import {Room} from "./dtos/room";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {RoomSettingsModalComponent} from "./components/room/room-settings-modal/room-settings-modal.component";
import {SidebarComponent} from "./components/sidebar/sidebar.component";
import {Group} from "./dtos/group";
import {GroupService} from "./services/group.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  @ViewChild(SidebarComponent) sidebar: SidebarComponent;

  title = 'frontend';

  defaultRoomName = 'select a room';
  room: Room = {
    title: this.defaultRoomName,
  };

  groups: Group[] = [];
  constructor(
    private modalService: NgbModal,
    private groupService: GroupService,
  ) {
  }

  roomChanged(room: Room) {
    this.room = room;
    this.loadGroups();
  }

  async openRoomSettings() {
    if (this.room.title === this.defaultRoomName) {
      return;
    }
    const modalRef = this.modalService.open(RoomSettingsModalComponent,
      {centered: true});
    modalRef.componentInstance.room = this.room;
    try {
      await modalRef.result;
    } catch (dismissReason) {
      console.log('dismissed reason: ', dismissReason);
      if (dismissReason === 'updated' || dismissReason === 'deleted') {
        this.sidebar.loadRooms();
      }
    }
  }

  private loadGroups() {
    if(!this.room.id) return;

    this.groupService.getAll(this.room.id).subscribe( {
      next: data => {
        this.groups = data;
      },
      error: e => {
        console.error('Could not load groups: ', e);
      }
    });
  }
}
