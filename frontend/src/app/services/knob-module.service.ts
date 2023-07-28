import { Injectable } from '@angular/core';
import {Globals} from "../global/globals";
import {HttpClient} from "@angular/common/http";
import {KnobModule} from "../dtos/knobModule";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class KnobModuleService {

  private moduleBaseUri: string = this.globals.backendUri + '/rooms';
  constructor(
    private http: HttpClient,
    private globals: Globals,
  ) { }

  getAll(room_id: number, group_id: number): Observable<KnobModule[]> {
    console.log('get all modules');
    return this.http.get<KnobModule[]>(this.moduleBaseUri + '/' + room_id + '/groups/' + group_id + "/knobmodules");
  }
}
