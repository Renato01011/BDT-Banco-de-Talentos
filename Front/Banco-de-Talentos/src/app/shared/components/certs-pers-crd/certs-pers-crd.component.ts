import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'shared-certs-pers-crd',
  templateUrl: './certs-pers-crd.component.html',
  styleUrls: ['./certs-pers-crd.component.scss'],
})
export class CertsPersCrdComponent implements OnInit {
  addFileDialog: boolean = false;

  responsiveOptions: any[] = [];
  products: any[] = [];

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
    this.products = [
      {
        name: '1',
      },
      {
        name: '2',
      },
      {
        name: '3',
      },
      {
        name: '4',
      },
      {
        name: '5',
      },
      {
        name: '6',
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
