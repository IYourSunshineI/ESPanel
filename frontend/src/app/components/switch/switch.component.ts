import {Component, Input} from '@angular/core';
import {EspService} from "../../services/esp.service";

@Component({
  selector: 'app-switch',
  templateUrl: './switch.component.html',
  styleUrls: ['./switch.component.scss']
})
export class SwitchComponent {
  @Input() title: string;

  constructor(
    private espService: EspService
  ) {
  }

  valueChanged(event: any): void{
    console.log(event.target.checked);
    this.espService.update(event.target.checked).subscribe({
      next: data => {
        console.log(data);
      },
      error: err => {
        console.error(err);
      }
    })
  }
}
