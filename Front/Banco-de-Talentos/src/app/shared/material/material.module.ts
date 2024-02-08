import { NgModule } from '@angular/core';

import { ButtonModule } from 'primeng/button';
import { PanelModule } from 'primeng/panel';
import { ToolbarModule } from 'primeng/toolbar';
import { SplitButtonModule } from 'primeng/splitbutton';
import { InputTextModule } from 'primeng/inputtext';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { CardModule } from 'primeng/card';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { CheckboxModule } from 'primeng/checkbox';
import { DropdownModule } from 'primeng/dropdown';

@NgModule({
  exports: [
    ButtonModule,
    PanelModule,
    ToolbarModule,
    SplitButtonModule,
    InputTextModule,
    ScrollPanelModule,
    CardModule,
    ToggleButtonModule,
    CheckboxModule,
    DropdownModule,
  ],
})
export class MaterialModule {}
