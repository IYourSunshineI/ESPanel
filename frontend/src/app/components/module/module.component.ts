import {Component, Input} from '@angular/core';
import {ModuleType} from "../../types/module-type";
import {KnobModule, DimmerModule, RgbModule} from "../../dtos/knobModule";

@Component({
  selector: 'app-module',
  templateUrl: './module.component.html',
  styleUrls: ['./module.component.scss']
})
export class ModuleComponent {
  @Input() module: KnobModule;
  @Input() type: ModuleType;
  protected readonly ModuleType = ModuleType;


  getInitialDimmerValue(): number {
    return (this.module as DimmerModule).brightness;
  }
}
