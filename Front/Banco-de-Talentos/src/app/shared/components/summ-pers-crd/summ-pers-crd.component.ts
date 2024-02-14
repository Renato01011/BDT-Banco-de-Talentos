import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'shared-summ-pers-crd',
  templateUrl: './summ-pers-crd.component.html',
  styleUrls: ['./summ-pers-crd.component.scss'],
})
export class SummPersCrdComponent implements OnInit {
  softSkillsDialog: boolean = false;

  constructor() {}

  ngOnInit(): void {}

  handleClick(): void {
    this.softSkillsDialog = true;
  }
  openNewSoftSkillDialog() {
    this.softSkillsDialog = true;
  }

  hideNewSoftSkillDialog() {
    this.softSkillsDialog = false;
  }
}
