import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'shared-feedback-crd',
  templateUrl: './feedback-crd.component.html',
  styleUrls: ['./feedback-crd.component.scss'],
})
export class FeedbackCrdComponent implements OnInit {
  rating: number = 0;
  softSkillsDialog: boolean = false;
  constructor() {}

  ngOnInit(): void {}
  handleClick(): void {
    this.softSkillsDialog = true;
  }
}
