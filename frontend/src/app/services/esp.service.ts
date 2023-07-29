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

  /**
   * Update the state of a group
   * @param ipAdress the ip adress of the esp corresponding to the group
   * @param active true if the group should be active
   */
  setState(ipAdress: string, active: boolean): Observable<void> {
    console.log('update group status: ' + active + ' for ip: ' + ipAdress);
    return this.http.put<void>('http://' + ipAdress + ':8081/led', active);
  }

  /**
   * Update the color of a module
   * @param ipAdress the ip adress of the esp corresponding to the group which holds the module
   * @param pinNumber the used pin
   * @param color the color to set
   */
  setColor(ipAdress: string, pinNumber: number, color: string): Observable<void> {
    console.log('update module color: ' + color + ' for ip: ' + ipAdress);
    return this.http.put<void>('http://' + ipAdress + ':8081/rgb?pinNumber=' + pinNumber, color);
  }

  /**
   * Update the brightness of a module
   * @param ipAdress the ip adress of the esp corresponding to the group which holds the module
   * @param pinNumber the used pin
   * @param brightness the brightness to set
   */
  setBrightness(ipAdress: string, pinNumber: number, brightness: number): Observable<void> {
    console.log('update module brightness: ' + brightness + ' for ip: ' + ipAdress);
    return this.http.put<void>('http://' + ipAdress + ':8081/dimmer?pinNumber=' + pinNumber, brightness);
  }
}
