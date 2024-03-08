import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { tap } from 'rxjs';
import { CustomTalent } from '../../../shared/models/interfaces/customTalent.interfaces';
import { FilterRequest } from 'src/app/shared/models/interfaces/filterReq.interfaces';
import { FilterResponse } from 'src/app/shared/models/interfaces/filterResp.interfaces';
import { FilterService } from '../../../core/services/filter/filter.service';
import { LoaderService } from 'src/app/core/services/loader/loader.service';
import { TalentService } from '../../../core/services/talent/talent.service';
import { ToastService } from '../../../core/services/toast/toast.service';
import {
  Document,
  EducationalExperience,
  LanguageLevel,
  SoftSkill,
  TalentResponse,
  TechnicalAbility,
  WorkExperience,
} from 'src/app/shared/models/interfaces/talentResp.interfaces';

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

  public selId?: number;
  public talentId?: number;
  public talents: FilterResponse[] = [];

  public customTalent?: CustomTalent;
  public description: string = '';
  public documents: Document[] = [];
  public educExp: EducationalExperience[] = [];
  public langProficiency: LanguageLevel[] = [];
  public softSkills: SoftSkill[] = [];
  public techSkills: TechnicalAbility[] = [];
  public workExp: WorkExperience[] = [];

  constructor(
    private filterService: FilterService,
    private loaderService: LoaderService,
    private router: Router,
    private talentService: TalentService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.toastService.addProperties('info', 'Info', '🥳');
    this.getTalentList(this.filterReq);
  }

  public filterTalent(filter: FilterRequest): void {
    this.getTalentList(filter);
  }

  private getTalentList(filter: FilterRequest): void {
    this.loaderService.showLoader();
    this.filterService.filterTalent(filter).subscribe({
      next: (talents) => {
        this.talents = talents;
        this.firstCall();
        this.loaderService.hideLoader();
      },
    });
  }

  public searchByTalentId(id: number): void {
    this.loaderService.showLoader();
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
          this.loaderService.hideLoader();
        },
      });
  }

  public firstCall(): void {
    if (this.talents && this.talents.length > 0) {
      const [firstObjeto] = this.talents;
      const firstId = firstObjeto.id;
      this.searchByTalentId(firstId);
    }
  }

  public newTalent(talent: TalentResponse): void {
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
