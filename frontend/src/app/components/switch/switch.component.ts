import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-switch',
  templateUrl: './switch.component.html',
  styleUrls: ['./switch.component.scss']
})
export class SwitchComponent {
  @Input() title: string;

  valueChanged(event: any): void{

  }
}
