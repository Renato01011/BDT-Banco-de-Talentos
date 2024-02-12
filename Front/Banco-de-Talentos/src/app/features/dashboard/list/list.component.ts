import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { Observer, interval, of, takeUntil, timer } from 'rxjs';
import { LoaderService } from 'src/app/core/services/loader/loader.service';

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

interface Soft {
  name: string;
}

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  constructor(private router: Router, private loaderService: LoaderService) {}

  englishLevel: English[] = [];
  skills: Skills[] = [];
  favorites: Favorite[] = [];
  selectedSkills: string[] = [];
  selectedEnglishLevel: string[] = [];
  selectedFavorite: string[] = [];
  rating: number = 0;

  skillDialog: boolean = false;

  technicalSkills: Favorite[] = [];

  inlineTechnicalSkills: any[] = [];

  softSkills: Soft[] = [];

  resume: MenuItem[] = [];

  toggleBtnOnIcon: string = 'pi pi-heart-fill';
  toggleBtnOffIcon: string = 'pi pi-heart';

  observer: Observer<any> = {
    next: (value) => console.log('[next]:', value),
    error: (error) => console.warn('[error]:', error),
    complete: () => this.loaderService.hideLoader(),
  };

  ngOnInit(): void {
    this.loaderService.showLoader('Loading');
    const interval$ = interval(1000);
    const cancel$ = timer(3000);
    const subs = interval$.pipe(takeUntil(cancel$)).subscribe(this.observer);

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

    this.technicalSkills = [
      { name: 'Docker', code: '1.3' },
      { name: 'Express', code: '2' },
      { name: 'Github', code: '3.5' },
      { name: 'Data Structure', code: '4' },
      { name: 'SQL', code: '4' },
      { name: 'NoSQL', code: '4' },
      { name: 'Node.js', code: '5' },
    ];

    this.inlineTechnicalSkills = this.crearNuevoArray();

    this.softSkills = [
      { name: 'Trabajo en equipo' },
      { name: 'Empatía' },
      { name: 'Resolución de problemas' },
      { name: 'Adaptabilidad' },
    ];
  }

  onNewTalent() {
    this.router.navigateByUrl('/home/talent');
  }

  openNew() {
    this.skillDialog = true;
  }

  hideDialog() {
    this.skillDialog = false;
  }

  crearNuevoArray(): { name: string }[] {
    const newArray = this.technicalSkills.map((skill) => ({
      name: `${skill.name} - ${skill.code}`,
    }));
    return newArray;
  }

  handleClick(): void {
    this.skillDialog = true;
  }
}
