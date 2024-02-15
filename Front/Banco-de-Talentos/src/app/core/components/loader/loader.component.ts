import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { Loader, LoaderService } from '../../services/loader/loader.service';

@Component({
  selector: 'app-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.scss'],
})
export class LoaderComponent implements OnInit {
  loader$: Subject<Loader> = this.loaderService.loader$;

  isLoading: boolean = false;
  msg: string = '';

  constructor(
    private loaderService: LoaderService,
    private changeDetector: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    console.log('loader');

    this.loader$.subscribe((loader) => {
      console.log(loader);

      this.isLoading = loader.status;
      this.msg = loader.msg;
      this.changeDetector.detectChanges();
    });
  }
}
