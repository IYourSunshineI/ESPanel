import {AfterContentInit, Component} from '@angular/core';
import {Room} from "../../dtos/room";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Group} from "../../dtos/group";
import {GroupService} from "../../services/group.service";

@Component({
  selector: 'app-settings-modal',
  templateUrl: './settings-modal.component.html',
  styleUrls: ['./settings-modal.component.scss']
})
export class SettingsModalComponent implements AfterContentInit {

  room: Room;
  updatedRoom: Room;

  groups: Group[] = [];
  showGroups: boolean = false;

  selectedIndex: number = 0;

  dismissReason: string = '';

  constructor(
    public activeModal: NgbActiveModal,
    private groupService: GroupService,
  ) {
  }

  ngAfterContentInit(): void {
    this.updatedRoom = {...this.room};
    this.loadGroups();
  }

  updateRoom($event: Room) {
    this.room = $event;
    this.updatedRoom = {...this.room};
    this.dismissReason = 'updated';
  }

  updateGroup($event: Group) {
    this.groups[this.selectedIndex - 2] = $event;
    this.dismissReason = 'updated';
  }

  loadGroups() {
    if(this.groups.length > 0) return;
    if(!this.room.id) return;

    this.groupService.getAll(this.room.id).subscribe({
      next: data => {
        this.groups = data;
      },
      error: e => {
        console.error(e);
      }
    });
  }

}
