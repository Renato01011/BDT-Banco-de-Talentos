import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { LayoutComponent } from './layout/layout.component';
import { ListComponent } from './list/list.component';
import { MaterialModule } from 'src/app/shared/material/material.module';

@NgModule({
  declarations: [LayoutComponent, ListComponent],
  imports: [CommonModule, DashboardRoutingModule, MaterialModule],
})
export class DashboardModule {}
