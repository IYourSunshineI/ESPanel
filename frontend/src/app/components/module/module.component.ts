import {Component, Input, OnInit} from '@angular/core';
import {ModuleType} from "../../types/module-type";
import {KnobModule, DimmerModule, RgbModule} from "../../dtos/knobModule";
import {debounceTime, fromEvent, Subject} from "rxjs";
import {DimmerModuleService} from "../../services/dimmer-module.service";
import {RgbModuleService} from "../../services/rgb-module.service";

@Component({
  selector: 'app-module',
  templateUrl: './module.component.html',
  styleUrls: ['./module.component.scss']
})
export class ModuleComponent implements OnInit{
  @Input() module: KnobModule;
  @Input() type: ModuleType;
  protected readonly ModuleType = ModuleType;

  rgbValue: { R: number; B: number; G: number } | null = null;

  filterSubject: Subject<any>;

  constructor(
    private dimmerModuleService: DimmerModuleService,
    private rgbModuleService: RgbModuleService,
  ) { }

  //TODO: Add lower debounce time for esp communication
  ngOnInit() {
    if(this.type === ModuleType.dimmer) {
      this.filterSubject = new Subject<number>();
      this.filterSubject.pipe(debounceTime(500)).subscribe(n => {
        console.log(n);
        (this.module as DimmerModule).brightness = n;

        if(!this.module.group_id) return;
        this.dimmerModuleService.update(this.module.group_id, this.module as DimmerModule).subscribe({
          next: data => {
            this.module = data;
            console.log('updated dimmer: ', data);
          },
          error: e=> {
            console.error('error updating dimmer: ', e);
          }
        });
      });
    } else {
      this.filterSubject = new Subject<[string, number]>();
      this.filterSubject.pipe(debounceTime(500)).subscribe(n => {
        // @ts-ignore
        this.rgbValue[n[0]] = n[1];
        console.log(this.rgbValue);
        if(!this.rgbValue) return;
        (this.module as RgbModule).color = this.rgbToHex(this.rgbValue['R'], this.rgbValue['G'], this.rgbValue['B']);

        if(!this.module.group_id) return;
        this.rgbModuleService.update(this.module.group_id, this.module as RgbModule).subscribe({
          next: data => {
            this.module = data;
            console.log('updated rgb: ', data);
          },
          error: e=> {
            console.error('error updating rgb: ', e);
          }
        });
      });
    }
  }

  getInitialDimmerValue(): number {
    return (this.module as DimmerModule).brightness;
  }

  dimmerValueChanged(value: number) {
    this.filterSubject.next(value);
  }

  getInitialRgbValue(i: string): number {
    if(!this.rgbValue) {
       this.rgbValue = this.hexToRgb((this.module as RgbModule).color);
    }
    if(!this.rgbValue) return 0;
    // @ts-ignore
    return this.rgbValue[i];
  }

  rgbValueChanged(i: string, value: number) {
    this.filterSubject.next([i, value]);
  }

  private hexToRgb(hex: string): { R: number; B: number; G: number } | null {
    const result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return result ? {
      'R': parseInt(result[1], 16),
      'G': parseInt(result[2], 16),
      'B': parseInt(result[3], 16)
    } : null;
  }

  private rgbToHex(r: number, g: number, b: number): string {
    return "#" + this.componentToHex(r) + this.componentToHex(g) + this.componentToHex(b);
  }

  private componentToHex(c: number): string {
    const hex = c.toString(16);
    return hex.length === 1 ? "0" + hex : hex;
  }
}
