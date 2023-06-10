import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EspService {

  constructor(
    private http: HttpClient,
  ) { }

  update(active: boolean): Observable<boolean> {
    console.log('update led status');
    return this.http.put<boolean>('http://192.168.8.155:8081/led', active);
  }
}
