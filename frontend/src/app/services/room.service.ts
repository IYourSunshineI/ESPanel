import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Room} from "../dtos/room";
import {Observable} from "rxjs";
import {Globals} from "../global/globals";

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private roomBaseUri: string = this.globals.backendUri + '/rooms';

  constructor(
    private http: HttpClient,
    private globals: Globals,
  ) { }

  /**
   * Creates new room
   * @param room to create
   * @return the created room
   */
  create(room: Room): Observable<Room>{
    console.log('create room: ', room);
    return this.http.post<Room>(this.roomBaseUri, room);
  }

  /**
   * Returns all rooms
   * @return all rooms
   */
  getAll(): Observable<Room[]> {
    console.log('get all rooms');
    return this.http.get<Room[]>(this.roomBaseUri);
  }
}
