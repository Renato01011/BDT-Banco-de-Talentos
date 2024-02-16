import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
<<<<<<< Updated upstream
=======
import { MasterRespConst } from 'src/app/core/global/constants/master-resp.constants';
import * as MasterModels from 'src/app/shared/models/interfaces/master.interfaces';
>>>>>>> Stashed changes

@Component({
  selector: 'app-new-talent',
  templateUrl: './new-talent.component.html',
  styleUrls: ['./new-talent.component.scss']
})
export class NewTalentComponent implements OnInit {

<<<<<<< Updated upstream
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
=======
  profiles: MasterModels.ProfileModel[] = [];
  levels: MasterModels.LangProficiencyModel[] = [];
  languages: MasterModels.LanguageModel[] = [];
  coins: MasterModels.CurrenciesModel[] = [];

  selectedCoin: any = null;

  constructor(private router: Router) {}
>>>>>>> Stashed changes

  ngOnInit(): void {
    this.languages = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_LANG) || '{}');
    this.levels = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_PROFICIENCY) || '{}');
    this.profiles = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_PROFILES) || '{}');
    this.coins = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_CURRENCIES) || '{}');
    console.log(JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_COUNTRY_CITY) || '{}'));
  }

  onVolver() {
    this.router.navigateByUrl('/home/dashboard/list');
  }

}
