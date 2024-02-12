import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { Loader } from '../../services/loader/loader.service';
import { LoaderService } from '../../services/loader/loader.service';
import { LOADING_MESSAGE } from '../../global/constants/constants';

@Component({
  selector: 'app-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.scss'],
})
export class LoaderComponent implements OnInit {
  loader$: Subject<Loader> = this.loaderService.loader$;

  isLoading: boolean = false;
  msg: string = LOADING_MESSAGE;

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
