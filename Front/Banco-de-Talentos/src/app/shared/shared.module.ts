import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { MaterialModule } from './material/material.module';
import { ControlPanelComponent } from './components/control-panel/control-panel.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [HeaderComponent, ControlPanelComponent],
  imports: [CommonModule, MaterialModule, FormsModule],
  exports: [HeaderComponent, ControlPanelComponent],
})
export class SharedModule {}
