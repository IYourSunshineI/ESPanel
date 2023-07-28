import { Component } from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {KnobModule} from "../../../dtos/knobModule";
import {ModuleType} from "../../../types/module-type";
import {KnobModuleService} from "../../../services/knob-module.service";

@Component({
  selector: 'app-module-create-modal',
  templateUrl: './module-create-modal.component.html',
  styleUrls: ['./module-create-modal.component.scss']
})
export class ModuleCreateModalComponent {

    knobModule: KnobModule = {
      title: '',
      pinNumber: 0,
    }
    room_id: number;
    group_id: number;

    type: ModuleType = ModuleType.dimmer;

    errorTitle: boolean = false;
    errorMessageTitle: string | string[] = '';
    errorPinNumber: boolean = false;
    errorMessagePinNumber: string | string[] = '';

    constructor(
      public activeModal: NgbActiveModal,
      private service: KnobModuleService,
    ) { }

    radioChangeHandler(event: any){
      if(event.target.value === 'rgb')
        this.type = ModuleType.rgb;
      else
        this.type = ModuleType.dimmer;
    }

    createModule() {
      if(!this.room_id || !this.group_id) return;

      this.service.create(this.room_id, this.group_id, this.knobModule, this.type).subscribe( {
        next: data => {
          console.log('created module: ', data);
          this.activeModal.dismiss('created');
        },
        error: e => {
          console.error('error creating module: ', e.error);
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
