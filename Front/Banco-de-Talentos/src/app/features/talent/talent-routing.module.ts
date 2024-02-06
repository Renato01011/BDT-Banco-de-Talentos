import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { NewTalentComponent } from './new-talent/new-talent.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'new-talent',
        component: NewTalentComponent,
      },
      {
        path: '**',
        redirectTo: 'new-talent',
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TalentRoutingModule {}
