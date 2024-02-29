import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { LoaderService } from 'src/app/core/services/loader/loader.service';
import { ToastService } from '../../../core/services/toast/toast.service';
import { FilterRequest } from 'src/app/shared/models/interfaces/filterReq.interfaces';
import { FilterService } from '../../../core/services/filter/filter.service';
import { FilterResponse } from 'src/app/shared/models/interfaces/filterResp.interfaces';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  public filterReq: FilterRequest = {
    habilities: '',
    languageIds: '',
    levelIds: '',
    nameJobTitle: '',
  };

  public talents: FilterResponse[] = [];

  constructor(
    private router: Router,
    private loaderService: LoaderService,
    private toastService: ToastService,
    private filterService: FilterService
  ) {}

  ngOnInit(): void {
    this.toastService.addProperties('info', 'Info', '🥳');
    this.getTalentList(this.filterReq);
  }

  filterTalent(filter: FilterRequest): void {
    this.getTalentList(filter);
  }

  getTalentList(filter: FilterRequest): void {
    this.filterService.filterTalent(filter).subscribe({
      next: (talents) => {
        console.log(talents);
        this.talents = talents;
      },
    });
  }
}
