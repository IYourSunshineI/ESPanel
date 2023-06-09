import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { GroupComponent } from './components/group/group.component';
import { SwitchComponent } from './components/switch/switch.component';
import { RadialSliderComponent } from './components/radial-slider/radial-slider.component';
import { ModuleComponent } from './components/module/module.component';

@NgModule({
  declarations: [
    AppComponent,
    GroupComponent,
    SwitchComponent,
    RadialSliderComponent,
    ModuleComponent,
  ],
  imports: [
    BrowserModule
  ],
  providers: [
    { provide: Window, useValue: window }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
