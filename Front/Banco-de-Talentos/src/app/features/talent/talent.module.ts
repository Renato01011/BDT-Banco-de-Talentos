import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TalentRoutingModule } from './talent-routing.module';
import { LayoutComponent } from './layout/layout.component';
import { NewTalentComponent } from './new-talent/new-talent.component';
import { MaterialModule } from 'src/app/shared/material/material.module';


@NgModule({
  declarations: [
    LayoutComponent,
    NewTalentComponent
  ],
  imports: [
    CommonModule,
    TalentRoutingModule,
    MaterialModule
  ]
})
export class TalentModule { }
