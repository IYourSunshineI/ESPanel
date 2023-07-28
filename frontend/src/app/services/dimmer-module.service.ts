import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Globals} from "../global/globals";

@Injectable({
  providedIn: 'root'
})
export class DimmerModuleService {

  private dimmerModuleBaseUri: string = this.globals.backendUri + '/rooms';

  constructor(
    private http: HttpClient,
    private globals: Globals,
  ) { }
}
