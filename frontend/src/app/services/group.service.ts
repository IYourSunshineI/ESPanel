import { Injectable } from '@angular/core';
import {Globals} from "../global/globals";
import {HttpClient} from "@angular/common/http";
import {Group} from "../dtos/group";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  private groupBaseUri: string = this.globals.backendUri + '/rooms';

  constructor(
    private http: HttpClient,
    private globals: Globals,
  ) { }

  /**
   * Creates new group
   * @param room_id the room id
   * @param group to create
   * @return the created group
   */
  create(room_id: number, group: Group): Observable<Group>{
    console.log('create group: ', group);
    return this.http.post<Group>(this.groupBaseUri + '/' + room_id + "/groups", group);
  }

  /**
   * Returns all groups
   * @param room_id the room id
   * @return all groups
   */
  getAll(room_id: number): Observable<Group[]> {
    console.log('get all groups');
    return this.http.get<Group[]>(this.groupBaseUri + '/' + room_id + "/groups");
  }

  /**
   * Updates the group
   * @param room_id the room id
   * @param group the group to update
   * @return the updated group
   */
  update(room_id: number, group: Group): Observable<Group> {
    console.log('update group: ', group);
    return this.http.put<Group>(this.groupBaseUri + '/' + room_id + "/groups" + '/' + group.id, group);
  }

  /**
   * Deletes the group
   * @param room_id the room id
   * @param id of the group to delete
   */
  delete(room_id: number, id: number): Observable<any> {
    console.log('delete group: ', id);
    return this.http.delete<any>(this.groupBaseUri + '/' + room_id + "/groups" + '/' + id);
  }
}
