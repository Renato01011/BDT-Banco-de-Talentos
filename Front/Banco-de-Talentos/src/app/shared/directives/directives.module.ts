import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmptyHrefDirective } from './empty-href/empty-href.directive';

@NgModule({
  declarations: [EmptyHrefDirective],
  imports: [CommonModule],
  exports: [EmptyHrefDirective],
})
export class DirectivesModule {}
