import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-new-talent',
  templateUrl: './new-talent.component.html',
  styleUrls: ['./new-talent.component.scss']
})
export class NewTalentComponent implements OnInit {

  niveles: any[];
  idiomas: any[];

  constructor() {
    this.idiomas = [
      { name: 'Ingles', code: 'in' },
      { name: 'Español', code: 'es' }
    ];

    this.niveles = [
      { name: 'Básico', code: '0' },
      { name: 'Intermedio', code: '1' },
      { name: 'Avanzado', code: '2' },
      { name: 'Nativo', code: '3' },
    ]
  }

  ngOnInit(): void {
  }

}
