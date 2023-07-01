import {Component, Input} from '@angular/core';
import {ModuleType} from "../../types/module-type";
import {Group} from "../../dtos/group";

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
}
