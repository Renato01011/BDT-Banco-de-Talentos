import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'shared-feedback-crd',
  templateUrl: './feedback-crd.component.html',
  styleUrls: ['./feedback-crd.component.scss'],
})
export class FeedbackCrdComponent implements OnInit {
  rating: number = 0;
  newFeedbackDialog: boolean = false;
  constructor() {}

  ngOnInit(): void {}

  openNewFeedbackDialog() {
    this.newFeedbackDialog = true;
  }

  hideNewFeedbackDialog() {
    this.newFeedbackDialog = false;
  }
}
