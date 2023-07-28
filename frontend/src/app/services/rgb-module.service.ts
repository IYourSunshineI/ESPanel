import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Globals} from "../global/globals";

@Injectable({
  providedIn: 'root'
})
export class RgbModuleService {

  private rgbModuleBaseUri: string = this.globals.backendUri + '/rooms';

  constructor(
    private http: HttpClient,
    private globals: Globals,
  ) { }
}
