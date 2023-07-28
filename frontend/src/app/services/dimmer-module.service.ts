import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Globals} from "../global/globals";
import {DimmerModule, KnobModule} from "../dtos/knobModule";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DimmerModuleService {

  private dimmerModuleBaseUri: string = this.globals.backendUri + '/groups';

  constructor(
    private http: HttpClient,
    private globals: Globals,
  ) { }

  /**
   * Updates the dimmer module
   * @param group_id the group id
   * @param module the module to update
   * @return the updated module
   */
  update(group_id: number, module: DimmerModule): Observable<KnobModule> {
    console.log('update dimmer module: ', module);
    return this.http.put<KnobModule>(this.dimmerModuleBaseUri + '/' + group_id + '/dimmermodules' + '/' + module.id, module);
  }
}
