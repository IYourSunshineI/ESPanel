import { Component } from '@angular/core';
import {ModuleType} from "../../types/module-type";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.scss']
})
export class GroupComponent {

  temp(e: number){

  }

  protected readonly ModuleType = ModuleType;
}
