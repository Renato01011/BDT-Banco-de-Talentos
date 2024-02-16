import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-talent',
  templateUrl: './new-talent.component.html',
  styleUrls: ['./new-talent.component.scss']
})
export class NewTalentComponent implements OnInit {

  jobs: any[];
  levels: any[];
  languages: any[];
  coins: any[];

  selectedCoin: any = null;

  constructor(private router: Router) {
    
    this.languages = [
      { name: 'Ingles', code: 'in' },
      { name: 'Español', code: 'es' }
    ];

    this.levels = [
      { name: 'Básico', code: '0' },
      { name: 'Intermedio', code: '1' },
      { name: 'Avanzado', code: '2' },
      { name: 'Nativo', code: '3' },
    ]

    this.jobs = [
      { name: 'Full-stack Developer', code: '0' }
    ];

    this.coins = [
      { name: 'Soles', code: '0' },
      { name: 'Dolares', code: '1' }
    ];
  }

  ngOnInit(): void {
    
  }

  onVolver() {
    this.router.navigateByUrl('/home/dashboard/list');
  }

}
