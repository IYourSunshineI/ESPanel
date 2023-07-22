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

  constructor(
    private espService: EspService
  ) {
  }

  click(event: any) {
    this.valueChanged.emit(event.target.checked);
  }

  //TEMP !!!!
  //valueChanged(event: any): void{
  //  console.log(event.target.checked);
  //  this.espService.update(event.target.checked).subscribe({
  //    next: data => {
  //      console.log(data);
  //    },
  //    error: err => {
  //      console.error(err);
  //    }
  //  })
  //}
}
