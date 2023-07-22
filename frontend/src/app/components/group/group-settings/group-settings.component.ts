import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Group} from "../../../dtos/group";
import {GroupService} from "../../../services/group.service";

@Component({
  selector: 'app-group-settings',
  templateUrl: './group-settings.component.html',
  styleUrls: ['./group-settings.component.scss']
})
export class GroupSettingsComponent {

  @Input() group: Group;
  @Output() updatedGroup: EventEmitter<Group> = new EventEmitter<Group>();
  @Output() dismissReason: EventEmitter<string> = new EventEmitter<string>();
  errorTitle: boolean = false;
  errorMessageTitle: string | string[] = '';
  errorIp: boolean = false;
  errorMessageIp: string | string[] = '';

  constructor(
    private service: GroupService,
  ) { }

  cancel() {
    this.dismissReason.emit('cancel');
  }

  save() {
    if(!this.group.room_id) return;

    this.service.update(this.group.room_id, this.group).subscribe({
      next: data => {
        this.group = data;
        console.log('updated group: ', data);
        this.updatedGroup.emit(data);
      },
      error: e => {
        console.error('error updating group: ', e);
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

  delete() {
    if(!this.group.id || !this.group.room_id) return;

    this.service.delete(this.group.room_id, this.group.id).subscribe({
      next: data => {
        console.log('deleted group: ', data);
        this.dismissReason.emit('deleted');
      },
      error: e => {
        console.error('error deleting group: ', e);
      }
    });
  }
}
