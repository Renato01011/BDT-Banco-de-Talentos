import { NgModule } from '@angular/core';

import { ButtonModule } from 'primeng/button';
import { PanelModule } from 'primeng/panel';
import { ToolbarModule } from 'primeng/toolbar';
import { SplitButtonModule } from 'primeng/splitbutton';
import { InputTextModule } from 'primeng/inputtext';

@NgModule({
  exports: [
    ButtonModule,
    PanelModule,
    ToolbarModule,
    SplitButtonModule,
    InputTextModule,
  ],
})
export class MaterialModule {}
