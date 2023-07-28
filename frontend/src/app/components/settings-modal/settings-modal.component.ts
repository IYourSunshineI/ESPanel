import {AfterContentInit, Component} from '@angular/core';
import {Room} from "../../dtos/room";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Group} from "../../dtos/group";
import {GroupService} from "../../services/group.service";
import {DimmerModule, KnobModule} from "../../dtos/knobModule";
import {KnobModuleService} from "../../services/knob-module.service";
import {ModuleType} from "../../types/module-type";

@Component({
  selector: 'app-settings-modal',
  templateUrl: './settings-modal.component.html',
  styleUrls: ['./settings-modal.component.scss']
})
export class SettingsModalComponent implements AfterContentInit {

  room: Room;
  updatedRoom: Room;

  groups: Group[] = [];
  showGroups: boolean = false;

  modules: KnobModule[] = [];
  showModules: boolean = false;

  selectedIndex: number = 0;

  dismissReason: string = '';

  constructor(
    public activeModal: NgbActiveModal,
    private groupService: GroupService,
    private knobModuleService: KnobModuleService,
  ) {
  }

  ngAfterContentInit(): void {
    this.updatedRoom = {...this.room};
    this.loadGroups();
  }

  updateRoom($event: Room) {
    this.room = $event;
    this.updatedRoom = {...this.room};
    this.dismissReason = 'updated';
  }

  updateGroup($event: Group) {
    this.groups[this.selectedIndex - 2] = $event;
    this.dismissReason = 'updated';
  }

  updateModule($event: KnobModule) {
    this.modules[this.selectedIndex - this.groups.length - 3] = $event;
    this.dismissReason = 'updated';
  }

  loadGroups() {
    if(this.groups.length > 0) return;
    if(!this.room.id) return;

    this.groupService.getAll(this.room.id).subscribe({
      next: data => {
        this.groups = data;
        this.loadModules();
      },
      error: e => {
        console.error(e);
      }
    });
  }

  loadModules() {
    if(this.groups.length === 0) return;

    this.groups.forEach(group => {
      if(!group.id) return;
      this.knobModuleService.getAll(group.id).subscribe({
        next: data => {
          this.modules = this.modules.concat(data);
        },
        error: e => {
          console.error(e);
        }
      });
    });
    console.log(this.modules);
  }

  getModulesType(module: KnobModule): ModuleType {
    if((module as DimmerModule).brightness !== null){
      return ModuleType.dimmer;
    }
    return ModuleType.rgb;
  }

}
