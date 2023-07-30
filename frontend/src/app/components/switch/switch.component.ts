import {Component, EventEmitter, Input, Output} from '@angular/core';
import {EspService} from "../../services/esp.service";

@Component({
  selector: 'app-switch',
  templateUrl: './switch.component.html',
  styleUrls: ['./switch.component.scss']
})
export class SwitchComponent {
  @Input() title: string;
  @Input() checked: boolean;
  @Output() valueChanged: EventEmitter<boolean> = new EventEmitter<boolean>();

  click(event: any) {
    this.valueChanged.emit(event.target.checked);
  }
}
