import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

interface Skills {
  name: string;
  code: string;
}
interface Favorite {
  name: string;
  code: string;
}
interface English {
  name: string;
  code: string;
}
interface Language {
  name: string;
  code: string;
}

@Component({
  selector: 'shared-control-panel',
  templateUrl: './control-panel.component.html',
  styleUrls: ['./control-panel.component.scss'],
})
export class ControlPanelComponent implements OnInit {
  selectedSkills: string[] = [];
  skills: Skills[] = [];

  favorites: Favorite[] = [];
  selectedFavorite: string[] = [];

  level: English[] = [];

  language: Language[] = [];

  selectedEnglishLevel: string[] = [];

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.skills = [
      { name: 'Docker', code: '1' },
      { name: 'Express', code: '2' },
      { name: 'Github', code: '3' },
      { name: 'Data Structure', code: '4' },
      { name: 'Node.js', code: '5' },
    ];
    this.level = [
      { name: 'Básico', code: '1' },
      { name: 'Intermedio', code: '2' },
      { name: 'Avanzado', code: '3' },
      { name: 'Nativo', code: '4' },
    ];
    this.favorites = [
      { name: 'Mis favoritos', code: '1' },
      { name: 'Backups', code: '2' },
    ];
    this.language = [
      { name: 'Español', code: '1' },
      { name: 'Ingles', code: '2' },
    ];
  }

  onNewTalent() {
    this.router.navigateByUrl('/home/talent');
  }
}
