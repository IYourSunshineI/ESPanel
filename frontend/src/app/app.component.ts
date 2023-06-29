import { Component } from '@angular/core';
import {Room} from "./dtos/room";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';

  room: Room = {
    title: 'select a room',
  };

  roomChanged(room: Room) {
    this.room = room;
  }
}
