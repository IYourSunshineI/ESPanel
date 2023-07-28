import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Globals} from "../global/globals";

@Injectable({
  providedIn: 'root'
})
export class RgbModuleService {

  private rgbModuleBaseUri: string = this.globals.backendUri + '/groups';

  constructor(
    private http: HttpClient,
    private globals: Globals,
  ) { }
}
