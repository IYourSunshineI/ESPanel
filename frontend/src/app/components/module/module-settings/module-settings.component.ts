import {Component, EventEmitter, Input, Output} from '@angular/core';
import {DimmerModule, KnobModule, RgbModule} from "../../../dtos/knobModule";
import {DimmerModuleService} from "../../../services/dimmer-module.service";
import {RgbModuleService} from "../../../services/rgb-module.service";
import {ModuleType} from "../../../types/module-type";
import {KnobModuleService} from "../../../services/knob-module.service";

@Component({
  selector: 'app-module-settings',
  templateUrl: './module-settings.component.html',
  styleUrls: ['./module-settings.component.scss']
})
export class ModuleSettingsComponent {

  @Input() module: KnobModule;
  @Input() type: ModuleType;
  @Output() updatedModule: EventEmitter<KnobModule> = new EventEmitter<KnobModule>();
  @Output() dismissReason: EventEmitter<string> = new EventEmitter<string>();

  errorTitle: boolean = false;
  errorMessageTitle: string | string[] = '';
  errorPinNumber: boolean = false;
  errorMessagePinNumber: string | string[] = '';

  constructor(
    private dimmerService: DimmerModuleService,
    private rgbService: RgbModuleService,
    private knobModuleService: KnobModuleService,
  ) { }

  cancel() {
    this.dismissReason.emit('cancel');
  }

  save() {
    if(!this.module.group_id) return;

    if(this.type === ModuleType.dimmer){
      this.dimmerService.update(this.module.group_id, this.module as DimmerModule).subscribe({
        next: data => {
          this.module = data;
          console.log('updated module: ', data);
          this.updatedModule.emit(data);
        },
        error: e => {
          console.error('error updating module: ', e);
          if(e.status === 422){
            e.error.forEach((e: { field: string; defaultMessage: string | string[]; }) => {
              if(e.field === 'title'){
                this.errorTitle = true;
                this.errorMessageTitle = e.defaultMessage;
              }
              if(e.field === 'pinNumber'){
                this.errorPinNumber = true;
                this.errorMessagePinNumber = e.defaultMessage;
              }
            });
          }
          if(e.status === 409){
            this.errorPinNumber = true;
            this.errorMessagePinNumber = 'pin number already in use';
          }
        }
      });
    }
    if(this.type === ModuleType.rgb){
      this.rgbService.update(this.module.group_id, this.module as RgbModule).subscribe({
        next: data => {
          this.module = data;
          console.log('updated module: ', data);
          this.updatedModule.emit(data);
        },
        error: e => {
          console.error('error updating module: ', e);
          if(e.status === 422){
            e.error.forEach((e: { field: string; defaultMessage: string | string[]; }) => {
              if(e.field === 'title'){
                this.errorTitle = true;
                this.errorMessageTitle = e.defaultMessage;
              }
              if(e.field === 'pinNumber'){
                this.errorPinNumber = true;
                this.errorMessagePinNumber = e.defaultMessage;
              }
            });
          }
          if(e.status === 409){
            this.errorPinNumber = true;
            this.errorMessagePinNumber = 'pin number already in use';
          }
        }
      });
    }
  }

  delete() {
    if(!this.module.id || !this.module.group_id) return;

    this.knobModuleService.delete(this.module.group_id, this.module.id).subscribe({
      next: data => {
        console.log('deleted module: ', data);
        this.dismissReason.emit('deleted');},
      error: e => {
        console.error('error deleting module: ', e);
      }
    });
  }

}
