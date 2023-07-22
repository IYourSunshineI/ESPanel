import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { GroupComponent } from './components/group/group.component';
import { SwitchComponent } from './components/switch/switch.component';
import { RadialSliderComponent } from './components/radial-slider/radial-slider.component';
import { ModuleComponent } from './components/module/module.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HttpClientModule } from '@angular/common/http';
import { RoomCreateModalComponent } from './components/room/room-create-modal/room-create-modal.component';
import {FormsModule} from "@angular/forms";
import { GroupCreateModalComponent } from './components/group/group-create-modal/group-create-modal.component';
import { SettingsModalComponent } from './components/settings-modal/settings-modal.component';
import { RoomSettingsComponent } from './components/room/room-settings/room-settings.component';
import { GroupSettingsComponent } from './components/group/group-settings/group-settings.component';

@NgModule({
  declarations: [
    AppComponent,
    GroupComponent,
    SwitchComponent,
    RadialSliderComponent,
    ModuleComponent,
    SidebarComponent,
    RoomCreateModalComponent,
    GroupCreateModalComponent,
    SettingsModalComponent,
    RoomSettingsComponent,
    GroupSettingsComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    { provide: Window, useValue: window }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
