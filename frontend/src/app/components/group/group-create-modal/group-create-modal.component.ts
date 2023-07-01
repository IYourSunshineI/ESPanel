import {Component} from '@angular/core';
import {GroupService} from "../../../services/group.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Group} from "../../../dtos/group";

@Component({
  selector: 'app-group-create-modal',
  templateUrl: './group-create-modal.component.html',
  styleUrls: ['./group-create-modal.component.scss']
})
export class GroupCreateModalComponent {

  group: Group = {
    title: '',
    ip_address: '',
    state: false,
  }
  room_id: number;

  errorTitle: boolean = false;
  errorMessageTitle: string | string[] = '';
  errorIp: boolean = false;
  errorMessageIp: string | string[] = '';

  constructor(
    public activeModal: NgbActiveModal,
    private service: GroupService,
  ) { }

  createGroup() {
    if(!this.room_id) return;

    this.service.create(this.room_id, this.group).subscribe( {
      next: data => {
        console.log('created group: ', data);
        this.activeModal.dismiss('created');
      },
      error: e => {
        console.error('error creating group: ', e.error);
        if(e.status === 422){
          e.error.forEach((e: { field: string; defaultMessage: string | string[]; }) => {
            if(e.field === 'title'){
              this.errorTitle = true;
              this.errorMessageTitle = e.defaultMessage;
            }
            if(e.field === 'ip_address'){
              this.errorIp = true;
              this.errorMessageIp = e.defaultMessage;
            }
          });
        }
        if(e.status === 409){
          this.errorIp = true;
          this.errorMessageIp = 'ip address already in use';
        }
      }
    });
  }
}
