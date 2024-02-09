import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

interface Skills {
  name: string;
  code: string;
}

interface English {
  name: string;
  code: string;
}

interface Favorite {
  name: string;
  code: string;
}

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  constructor(private router: Router) {}

  englishLevel: English[] = [];
  skills: Skills[] = [];
  favorites: Favorite[] = [];
  selectedSkills: string[] = [];
  selectedEnglishLevel: string[] = [];
  selectedFavorite: string[] = [];
  rating: number = 0;

  resume: MenuItem[] = [];

  toggleBtnOnIcon: string = 'pi pi-heart-fill';
  toggleBtnOffIcon: string = 'pi pi-heart';

  ngOnInit(): void {
    this.englishLevel = [
      { name: 'Básico', code: '1' },
      { name: 'Intermedio', code: '2' },
      { name: 'Avanzado', code: '3' },
      { name: 'Nativo', code: '4' },
    ];
    this.skills = [
      { name: 'Docker', code: '1' },
      { name: 'Express', code: '2' },
      { name: 'Github', code: '3' },
      { name: 'Data Structure', code: '4' },
      { name: 'Node.js', code: '5' },
    ];
    this.favorites = [
      { name: 'Mis favoritos', code: '1' },
      { name: 'Backups', code: '2' },
    ];
    this.rating = 3;
    this.resume = [{ label: 'CV' }, { label: 'CV Fractal' }];
  }

  onNewTalent() {
    this.router.navigateByUrl('/home/talent');
  }
}
