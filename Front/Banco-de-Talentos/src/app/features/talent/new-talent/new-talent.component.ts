import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MasterRespConst } from 'src/app/core/global/constants/master-resp.constants';
import * as MasterModels from 'src/app/shared/models/interfaces/master.interfaces';

import { MasterService } from 'src/app/core/services/master/master.service';

@Component({
  selector: 'app-new-talent',
  templateUrl: './new-talent.component.html',
  styleUrls: ['./new-talent.component.scss']
})
export class NewTalentComponent implements OnInit, OnDestroy {
  
  profiles: MasterModels.ProfileModel[] = [];
  levels: MasterModels.LangProficiencyModel[] = [];
  languages: MasterModels.LanguageModel[] = [];
  coins: MasterModels.CurrenciesModel[] = [];

  technicalHabilities: any[] = [];
  softSkills: any[] = [];
  workExperience: any[] = [];
  educationalExperience: any[] = [];
  knownLanguages: any[] = [];

  selectedCoin: any = null;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.languages = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_LANG) || '{}');
    this.levels = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_PROFICIENCY) || '{}');
    this.profiles = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_PROFILES) || '{}');
    this.coins = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_CURRENCIES) || '{}');

    this.technicalHabilities.push({
      'technicalAbility': '',
      'years': ''
    });

    this.softSkills.push({
      'softSkill': ''
    });

    this.workExperience.push({
      'firm': '',
      'job': '',
      'initialDate': '',
      'finalDate': '',
      'currWorkingFlag': false,
    });

    this.educationalExperience.push({
      'institution': '',
      'major': '',
      'degree': '',
      'initialDate': '',
      'finalDate': '',
      'currWorkingFlag': false,
    });

    this.knownLanguages.push({
      'language': '',
      'level': ''
    });
  }

  ngOnDestroy(): void {
    this.technicalHabilities = [];
    this.softSkills = [];
    this.workExperience = [];
    this.educationalExperience = [];
    this.knownLanguages = [];
  }

  AddTechnicalAbility() {
    this.technicalHabilities.push({
      'technicalAbility': '',
      'years': ''
    });
  }
  DeleteTechnicalAbility(index: number) {
    this.technicalHabilities.splice(index, 1);
  }

  AddSoftSkill() {
    this.softSkills.push({
      'softSkill': ''
    });
  }
  DeleteSoftSkill(index: number) {
    this.softSkills.splice(index, 1);
  }

  AddWorkExperience() {
    this.workExperience.push({
      'firm': '',
      'job': '',
      'initialDate': '',
      'finalDate': '',
      'currWorkingFlag': false,
    });
  }
  DeleteWorkExperience(index: number) {
    this.workExperience.splice(index, 1);
  }

  AddEducationalExperience() {
    this.educationalExperience.push({
      'institution': '',
      'major': '',
      'degree': '',
      'initialDate': '',
      'finalDate': '',
      'currWorkingFlag': false,
    });
  }
  DeleteEducationalExperience(index: number) {
    this.educationalExperience.splice(index, 1);
  }

  AddKnownLanguage() {
    this.knownLanguages.push({
      'language': '',
      'level': ''
    });
  }
  DeleteKnownLanguage(index: number) {
    this.knownLanguages.splice(index, 1);
  }

  onVolver() {
    this.router.navigateByUrl('/home/dashboard/list');
  }

}
