import {AfterViewInit, Component, Input} from '@angular/core';
import {ModuleType} from "../../types/module-type";
import {Group} from "../../dtos/group";
import {GroupService} from "../../services/group.service";
import {KnobModuleService} from "../../services/knob-module.service";
import {KnobModule, RgbModule} from "../../dtos/knobModule";
import {ModuleCreateModalComponent} from "../module/module-create-modal/module-create-modal.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.scss']
})
export class GroupComponent implements AfterViewInit {

  @Input() group: Group;

  modules: KnobModule[] = [];

  protected readonly ModuleType = ModuleType;

  constructor(
    private modalService: NgbModal,
    private groupService: GroupService,
    private knobService: KnobModuleService,
  ) { }

  ngAfterViewInit(): void {
    this.loadModules();
  }

  async openCreateModuleModal() {
    const modalRef = this.modalService.open(ModuleCreateModalComponent,
      {centered: true});
    modalRef.componentInstance.room_id = this.group.room_id;
    modalRef.componentInstance.group_id = this.group.id;
    try {
      await modalRef.result;
    } catch (dismissReason) {
      console.log('dismissed reason: ', dismissReason);
      if(dismissReason === 'created') {
        this.loadModules();
      }
    }
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

    this.knobService.getAll(this.group.id).subscribe({
      next: data => {
        console.log('load modules', data);
        this.modules = data;
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
