import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MasterRespConst } from 'src/app/core/global/constants/master-resp.constants';
import * as MasterModels from 'src/app/shared/models/interfaces/master.interfaces';

import { MasterService } from 'src/app/core/services/master/master.service';

@Component({
  selector: 'app-new-talent',
  templateUrl: './new-talent.component.html',
  styleUrls: ['./new-talent.component.scss']
})
export class NewTalentComponent implements OnInit {
  
  profiles: MasterModels.ProfileModel[] = [];
  levels: MasterModels.LangProficiencyModel[] = [];
  languages: MasterModels.LanguageModel[] = [];
  coins: MasterModels.CurrenciesModel[] = [];

  selectedCoin: any = null;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.languages = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_LANG) || '{}');
    this.levels = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_PROFICIENCY) || '{}');
    this.profiles = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_PROFILES) || '{}');
    this.coins = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_CURRENCIES) || '{}');
  }

  onVolver() {
    this.router.navigateByUrl('/home/dashboard/list');
  }

}
