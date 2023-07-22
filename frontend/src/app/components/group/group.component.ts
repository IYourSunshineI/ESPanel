import {Component, Input} from '@angular/core';
import {ModuleType} from "../../types/module-type";
import {Group} from "../../dtos/group";
import {GroupService} from "../../services/group.service";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.scss']
})
export class GroupComponent {

  @Input() group: Group;

  temp(e: any){
    console.log('click');
  }

  protected readonly ModuleType = ModuleType;

  constructor(
    private service: GroupService,
  ) { }

  changeState(state: boolean) {
    if(!this.group.id || !this.group.room_id) return;
    this.group.state = state;
    this.service.update(this.group.room_id, this.group).subscribe({
      next: data => {
        console.log('update group', data);
        this.group = data;
      },
      error: e => {
        console.error('error on update group', e);
      }
    });
  }
}
