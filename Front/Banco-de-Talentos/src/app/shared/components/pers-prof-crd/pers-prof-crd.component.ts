import { Component, EventEmitter, Input, Output } from '@angular/core';

import { FilterResponse } from '../../models/interfaces/filterResp.interfaces';

@Component({
  selector: 'shared-pers-prof-crd',
  templateUrl: './pers-prof-crd.component.html',
  styleUrls: ['./pers-prof-crd.component.scss'],
})
export class PersProfCrdComponent {
  @Input()
  talents: FilterResponse[] = [];

  @Input()
  selId?: number;

  @Output()
  public talentId = new EventEmitter<number>();

  constructor() {}

  emitTalentId(id: number) {
    this.talentId.emit(id);
  }
}
