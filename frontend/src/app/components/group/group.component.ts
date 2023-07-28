import {AfterViewInit, Component, Input} from '@angular/core';
import {ModuleType} from "../../types/module-type";
import {Group} from "../../dtos/group";
import {GroupService} from "../../services/group.service";
import {KnobModuleService} from "../../services/knob-module.service";
import {KnobModule, RgbModule, DimmerModule} from "../../dtos/knobModule";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.scss']
})
export class GroupComponent implements AfterViewInit {

  @Input() group: Group;

  modules: KnobModule[] = [];

  temp(e: any){
    console.log('click');
  }

  protected readonly ModuleType = ModuleType;

  constructor(
    private groupService: GroupService,
    private knobService: KnobModuleService,
  ) { }

  ngAfterViewInit(): void {
    this.loadModules();
  }

  changeState(state: boolean) {
    if(!this.group.id || !this.group.room_id) return;
    this.group.state = state;
    this.groupService.update(this.group.room_id, this.group).subscribe({
      next: data => {
        console.log('update group', data);
        this.group = data;
      },
      error: e => {
        console.error('error on update group', e);
      }
    });
  }

  loadModules() {
    if(!this.group.id || !this.group.room_id) return;

    this.knobService.getAll(this.group.room_id, this.group.id).subscribe({
      next: data => {
        console.log('load modules', data);
        this.modules = data;
        this.modules.forEach(m => {
        })
      },
      error: e => {
        console.error('error on load modules', e);
      }
    });
  }

  isRgbModule(module: KnobModule): boolean {
    return (module as RgbModule).color !== null;
  }
}
