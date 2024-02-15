import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'shared-lang-pers-crd',
  templateUrl: './lang-pers-crd.component.html',
  styleUrls: ['./lang-pers-crd.component.scss'],
})
export class LangPersCrdComponent implements OnInit {

  rating: number = 0;

  newLanguageDialog: boolean = false;
  editLanguageDialog: boolean = false;

  languages: any[] = [];
  levels: any[] = [];

  constructor() {}

  ngOnInit(): void {
    this.languages = [
      { name: 'Ingles', code: 'in' },
      { name: 'Español', code: 'es' }
    ];
    this.levels = [
      { name: 'Básico', code: '1' },
      { name: 'Intermedio', code: '2' },
      { name: 'Avanzado', code: '3' },
      { name: 'Nativo', code: '4' },
    ];
  }

  openEditLanguageDialog() {
    this.editLanguageDialog = true;
  }

  hideEditLanguageDialog() {
    this.editLanguageDialog = false;
  }

  openNewLanguageDialog() {
    this.newLanguageDialog = true;
  }

  hideNewLanguageDialog() {
    this.newLanguageDialog = false;
  }

}
