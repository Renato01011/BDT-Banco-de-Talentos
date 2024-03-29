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
import { AuthService } from '../../../core/services/auth/auth.service';
import {
  Document,
  EducationalExperience,
  Feedbacks,
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
  public isRecruiter: boolean = false;

  public filterReq: FilterRequest = {
    userId: this.authService.idUser,
    habilities: '',
    languageIds: '',
    levelIds: '',
    nameJobTitle: '',
    userListIds: '',
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
  public feedbacks: Feedbacks[] = [];

  constructor(
    private filterService: FilterService,
    private loaderService: LoaderService,
    private talentService: TalentService,
    private authService: AuthService,
    private router: Router,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.getTalentList(this.filterReq);
    this.isRecruiter = this.authService.isRecruiter;
  }

  public filterTalent(filter: FilterRequest): void {
    this.filterReq = filter;
    this.getTalentList(this.filterReq);
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
    if (isNaN(id)) return;
    this.loaderService.showLoader();
    this.talentService
      .getTalentById(id, this.authService.idUser)
      .pipe(
        tap((talent) => this.updateTalentData(talent)),
        tap((talent) => this.newTalent(talent))
      )
      .subscribe({
        next: (talent) => {
          this.selId = talent.idTalent;
          this.loaderService.hideLoader();
        },
      });
  }

  private updateTalentData(talent: TalentResponse): void {
    const [, ...certifications] = talent.documents;
    this.documents = certifications;
    this.educExp = talent.educationalExperiences;
    this.langProficiency = talent.languageLevels;
    this.softSkills = talent.softSkills;
    this.techSkills = talent.technicalAbilities;
    this.workExp = talent.workExperiences;
    this.description = talent.description;
    this.feedbacks = talent.feedbacks;
  }

  public firstCall(): void {
    if (this.talents && this.talents.length > 0) {
      const [firstObjeto] = this.talents;
      const firstId = firstObjeto.id;
      this.searchByTalentId(firstId);
    }
  }

  public saveChanges(id: number): void {
    this.searchByTalentId(id);
    this.getTalentList(this.filterReq);
  }

  public newTalent(talent: TalentResponse): void {
    const [resume] = talent.documents;
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
      userListTalent,
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
      userListTalent,
      resume,
    };
  }

  public get isListEmpty(): boolean {
    return this.talents.length !== 0;
  }
}
