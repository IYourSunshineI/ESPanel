import {Component, Input} from '@angular/core';
import {ModuleType} from "../../types/module-type";
import {KnobModule, DimmerModule, RgbModule} from "../../dtos/knobModule";

@Component({
  selector: 'app-module',
  templateUrl: './module.component.html',
  styleUrls: ['./module.component.scss']
})
export class ModuleComponent {
  @Input() module: KnobModule;
  @Input() type: ModuleType;
  protected readonly ModuleType = ModuleType;

  rgbValue: { R: number; B: number; G: number } | null = null;


  getInitialDimmerValue(): number {
    return (this.module as DimmerModule).brightness;
  }

  getInitialRgbValue(i: string): number {
    if(!this.rgbValue) {
       this.rgbValue = this.hexToRgb((this.module as RgbModule).color);
    }
    if(!this.rgbValue) return 0;
    // @ts-ignore
    return this.rgbValue[i];
  }

  private hexToRgb(hex: string): { R: number; B: number; G: number } | null {
    const result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return result ? {
      'R': parseInt(result[1], 16),
      'G': parseInt(result[2], 16),
      'B': parseInt(result[3], 16)
    } : null;
  }
}
