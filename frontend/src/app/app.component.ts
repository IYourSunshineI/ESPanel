import {Component, ViewChild} from '@angular/core';
import {Room} from "./dtos/room";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {SidebarComponent} from "./components/sidebar/sidebar.component";
import {Group} from "./dtos/group";
import {GroupService} from "./services/group.service";
import {GroupCreateModalComponent} from "./components/group/group-create-modal/group-create-modal.component";
import {SettingsModalComponent} from "./components/settings-modal/settings-modal.component";

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
    const modalRef = this.modalService.open(SettingsModalComponent,
      {centered: true, size: 'xl'});
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

  async createGroup() {
    const modalRef = this.modalService.open(GroupCreateModalComponent,
      {centered: true});
    modalRef.componentInstance.room_id = this.room.id;
    try {
      await modalRef.result;
    } catch (dismissReason) {
      console.log('dismissed reason: ', dismissReason);
      if (dismissReason === 'created') {
        this.loadGroups();
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
