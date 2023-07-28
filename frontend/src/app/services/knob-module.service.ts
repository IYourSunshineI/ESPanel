import { Injectable } from '@angular/core';
import {Globals} from "../global/globals";
import {HttpClient} from "@angular/common/http";
import {KnobModule} from "../dtos/knobModule";
import {Observable} from "rxjs";
import {ModuleType} from "../types/module-type";

@Injectable({
  providedIn: 'root'
})
export class KnobModuleService {

  private moduleBaseUri: string = this.globals.backendUri + '/rooms';
  constructor(
    private http: HttpClient,
    private globals: Globals,
  ) { }

  /**
   * Creates new module of the given type
   * @param room_id the room id
   * @param group_id the group id
   * @param module to create
   * @param type the type of the module
   * @return the created module
   */
  create(room_id: number, group_id: number, module: KnobModule, type: ModuleType): Observable<KnobModule>{
    const mType = type === ModuleType.dimmer ? 'dimmermodules' : 'rgbmodules';
    console.log('create ' + mType + ': ', module);
    return this.http.post<KnobModule>(this.moduleBaseUri + '/' + room_id + '/groups/' + group_id + "/" + mType, module);
  }

  /**
   * Returns all modules of the given group
   * @param room_id the room id
   * @param group_id the group id
   * @return all modules of the given group
   */
  getAll(room_id: number, group_id: number): Observable<KnobModule[]> {
    console.log('get all modules');
    return this.http.get<KnobModule[]>(this.moduleBaseUri + '/' + room_id + '/groups/' + group_id + "/knobmodules");
  }
}
