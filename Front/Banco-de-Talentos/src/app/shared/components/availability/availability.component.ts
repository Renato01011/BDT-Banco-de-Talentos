import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-availability',
  templateUrl: './availability.component.html',
  styleUrls: ['./availability.component.scss']
})
export class AvailabilityComponent implements OnInit {

  @Input()
  public availability: string = '';
  @Input()
  public selectedId?: number;

  @Output()
  public talentId = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }

}
