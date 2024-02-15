import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Observer, interval, takeUntil, timer } from 'rxjs';
import { LoaderService } from 'src/app/core/services/loader/loader.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  constructor(private router: Router, private loaderService: LoaderService) {}

  observer: Observer<any> = {
    next: (value) => console.log('[next]:', value),
    error: (error) => console.warn('[error]:', error),
    complete: () => this.loaderService.hideLoader(),
  };

  ngOnInit(): void {
    this.loaderService.showLoader('Loading');
    const interval$ = interval(1000);
    const cancel$ = timer(1500);
    const subs = interval$.pipe(takeUntil(cancel$)).subscribe(this.observer);
  }
}
