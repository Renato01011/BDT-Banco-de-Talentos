import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  constructor(private router: Router) {}
  items: MenuItem[] = [];
  ngOnInit(): void {
    this.items = [
      { label: 'Básico' },
      { label: 'Intermedio' },
      { label: 'Avanzado' },
      { label: 'Nativo' },
    ];
  }

  onNewTalent() {
    this.router.navigateByUrl('/home/talent');
  }
}
