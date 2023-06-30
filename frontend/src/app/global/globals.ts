import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class Globals {
  readonly backendUri: string = this.findBackendUri();

  private findBackendUri(): string {
    if(window.location.port === '4200'){
      return 'http://localhost:8080';
    } else {
      return window.location.protocol + '//' + window.location.host + window.location.pathname;
    }
  }
}
