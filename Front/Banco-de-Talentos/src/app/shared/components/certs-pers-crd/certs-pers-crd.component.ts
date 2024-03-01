import { Component, Input, OnInit } from '@angular/core';
import { Document } from '../../models/interfaces/talentResp.interfaces';

@Component({
  selector: 'shared-certs-pers-crd',
  templateUrl: './certs-pers-crd.component.html',
  styleUrls: ['./certs-pers-crd.component.scss'],
})
export class CertsPersCrdComponent implements OnInit {
  @Input()
  documents: Document[] = [];

  addFileDialog: boolean = false;

  responsiveOptions: any[] = [];

  constructor() {}

  ngOnInit(): void {
    this.responsiveOptions = [
      {
        breakpoint: '768px',
        numVisible: 2,
        numScroll: 2,
      },

      {
        breakpoint: '560px',
        numVisible: 1,
        numScroll: 1,
      },
    ];
  }

  openAddFileDialog() {
    this.addFileDialog = true;
  }

  hideAddFileDialog() {
    this.addFileDialog = false;
  }
}
