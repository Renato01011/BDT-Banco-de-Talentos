import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-certs-pers-crd',
  templateUrl: './certs-pers-crd.component.html',
  styleUrls: ['./certs-pers-crd.component.scss']
})
export class CertsPersCrdComponent implements OnInit {

  addFileDialog: boolean = false;

  constructor() { }

  ngOnInit(): void {

  }

  openAddFileDialog() {
    this.addFileDialog = true;
  }

  hideAddFileDialog() {
    this.addFileDialog = false;
  }

}
