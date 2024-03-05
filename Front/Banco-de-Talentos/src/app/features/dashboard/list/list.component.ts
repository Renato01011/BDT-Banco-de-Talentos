import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { LoaderService } from 'src/app/core/services/loader/loader.service';
import { ToastService } from '../../../core/services/toast/toast.service';
import { FilterRequest } from 'src/app/shared/models/interfaces/filterReq.interfaces';
import { FilterService } from '../../../core/services/filter/filter.service';
import { FilterResponse } from 'src/app/shared/models/interfaces/filterResp.interfaces';
import { TalentService } from '../../../core/services/talent/talent.service';
import {
  Document,
  EducationalExperience,
  LanguageLevel,
  SoftSkill,
  TalentResponse,
  TechnicalAbility,
  WorkExperience,
} from 'src/app/shared/models/interfaces/talentResp.interfaces';
import { tap } from 'rxjs';
import { CustomTalent } from '../../../shared/models/interfaces/customTalent.interfaces';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  public filterReq: FilterRequest = {
    habilities: '',
    languageIds: '',
    levelIds: '',
    nameJobTitle: '',
  };

  public talents: FilterResponse[] = [];
  public talentId?: number;
  public selId?: number;

  description: string = '';

  documents: Document[] = [];
  softSkills: SoftSkill[] = [];
  techSkills: TechnicalAbility[] = [];
  workExp: WorkExperience[] = [];
  educExp: EducationalExperience[] = [];
  langProficiency: LanguageLevel[] = [];

  customTalent?: CustomTalent;

  constructor(
    private router: Router,
    private loaderService: LoaderService,
    private toastService: ToastService,
    private filterService: FilterService,
    private talentService: TalentService
  ) {}

  ngOnInit(): void {
    this.toastService.addProperties('info', 'Info', '🥳');
    this.getTalentList(this.filterReq);
  }

  filterTalent(filter: FilterRequest): void {
    this.getTalentList(filter);
  }

  getTalentList(filter: FilterRequest): void {
    this.filterService.filterTalent(filter).subscribe({
      next: (talents) => {
        this.talents = talents;
        this.firstCall();
      },
    });
  }

  searchByTalentId(id: number): void {
    this.talentService
      .getTalentById(id)
      .pipe(
        tap((talent) => (this.documents = talent.documents)),
        tap((talent) => (this.educExp = talent.educationalExperiences)),
        tap((talent) => (this.langProficiency = talent.languageLevels)),
        tap((talent) => (this.softSkills = talent.softSkills)),
        tap((talent) => (this.techSkills = talent.technicalAbilities)),
        tap((talent) => (this.workExp = talent.workExperiences)),
        tap((talent) => (this.description = talent.description)),
        tap((talent) => this.newTalent(talent))
      )
      .subscribe({
        next: (talent) => {
          this.selId = talent.idTalent;
        },
      });
  }

  public firstCall() {
    if (this.talents && this.talents.length > 0) {
      const [firstObjeto] = this.talents;
      const firstId = firstObjeto.id;
      this.searchByTalentId(firstId);
    }
  }

  public newTalent(talent: TalentResponse) {
    const {
      idTalent,
      name,
      surname,
      secondSurname,
      profilePicture,
      initialSalary,
      finalSalary,
      phone,
      linkedin,
      github,
      avgRating,
      miscData,
      feedbacks,
    } = talent;

    this.customTalent = {
      idTalent,
      name,
      surname,
      secondSurname,
      profilePicture,
      initialSalary,
      finalSalary,
      phone,
      linkedin,
      github,
      avgRating,
      miscData,
      feedbacks,
    };
  }
}
