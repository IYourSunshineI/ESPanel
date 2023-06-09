import {Component, Input} from '@angular/core';
import {ModuleType} from "../../types/module-type";

@Component({
  selector: 'app-module',
  templateUrl: './module.component.html',
  styleUrls: ['./module.component.scss']
})
export class ModuleComponent {
  @Input() type: ModuleType;
  protected readonly ModuleType = ModuleType;
}
