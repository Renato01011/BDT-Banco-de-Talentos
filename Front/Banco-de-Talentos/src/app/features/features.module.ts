import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FeaturesRoutingModule } from './features-routing.module';
import { LayoutComponent } from './layout/layout.component';
import { MaterialModule } from '../shared/material/material.module';

@NgModule({
  declarations: [LayoutComponent],
  imports: [CommonModule, FeaturesRoutingModule, MaterialModule],
})
export class FeaturesModule {}
