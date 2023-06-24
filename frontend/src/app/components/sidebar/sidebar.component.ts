import { Component } from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {RoomCreateModalComponent} from "../room/room-create-modal/room-create-modal.component";


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {

  constructor(
    private modalService: NgbModal,
  ) { }

  async openCreateRoomModal() {
    const modalRef = this.modalService.open(RoomCreateModalComponent,
      {centered: true});
    try {
      await modalRef.result;
      //TODO: refresh room list
    } catch (dismissReason) {
      console.log('dismissed reason: ', dismissReason);
    }
  }
}
