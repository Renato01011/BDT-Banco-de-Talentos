import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-talent',
  templateUrl: './new-talent.component.html',
  styleUrls: ['./new-talent.component.scss']
})
export class NewTalentComponent implements OnInit {

  puestos: any[];
  niveles: any[];
  idiomas: any[];
  tipoDeMoneda: any[];

  monedaSeleccionada: any = null;

  constructor(private router: Router) {
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

    this.puestos = [
      { name: 'Full-stack Developer', code: '0' }
    ];

    this.tipoDeMoneda = [
      { name: 'Soles', code: '0' },
      { name: 'Dolares', code: '1' }
    ];
  }

  ngOnInit(): void {
    this.monedaSeleccionada = this.tipoDeMoneda[1];
  }

  onVolver() {
    this.router.navigateByUrl('/home/dashboard/list');
  }

}
