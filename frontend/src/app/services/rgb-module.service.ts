import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Globals} from "../global/globals";
import {KnobModule, RgbModule} from "../dtos/knobModule";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RgbModuleService {

  private rgbModuleBaseUri: string = this.globals.backendUri + '/groups';

  constructor(
    private http: HttpClient,
    private globals: Globals,
  ) { }

  /**
   * Updates the rgb module
   * @param group_id the group id
   * @param module the module to update
   * @return the updated module
   */
  update(group_id: number, module: RgbModule): Observable<KnobModule> {
    console.log('update rgb module: ', module);
    return this.http.put<KnobModule>(this.rgbModuleBaseUri + '/' + group_id + '/rgbmodules' + '/' + module.id, module);
  }
}
