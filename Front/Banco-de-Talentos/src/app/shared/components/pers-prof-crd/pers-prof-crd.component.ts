import { Component, Input } from '@angular/core';
import { FilterService } from '../../../core/services/filter/filter.service';
import { FilterResponse } from '../../models/interfaces/filterResp.interfaces';

@Component({
  selector: 'shared-pers-prof-crd',
  templateUrl: './pers-prof-crd.component.html',
  styleUrls: ['./pers-prof-crd.component.scss'],
})
export class PersProfCrdComponent {
  @Input()
  talents: FilterResponse[] = [];

  constructor(private filterService: FilterService) {}
}
