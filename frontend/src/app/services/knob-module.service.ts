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

  private moduleBaseUri: string = this.globals.backendUri + '/groups';
  constructor(
    private http: HttpClient,
    private globals: Globals,
  ) { }

  /**
   * Creates new module of the given type
   * @param group_id the group id
   * @param module to create
   * @param type the type of the module
   * @return the created module
   */
  create(group_id: number, module: KnobModule, type: ModuleType): Observable<KnobModule>{
    const mType = type === ModuleType.dimmer ? 'dimmermodules' : 'rgbmodules';
    console.log('create ' + mType + ': ', module);
    return this.http.post<KnobModule>(this.moduleBaseUri + '/' + group_id + "/" + mType, module);
  }

  /**
   * Returns all modules of the given group
   * @param group_id the group id
   * @return all modules of the given group
   */
  getAll(group_id: number): Observable<KnobModule[]> {
    console.log('get all modules');
    return this.http.get<KnobModule[]>(this.moduleBaseUri + '/' + group_id + "/knobmodules");
  }
}
