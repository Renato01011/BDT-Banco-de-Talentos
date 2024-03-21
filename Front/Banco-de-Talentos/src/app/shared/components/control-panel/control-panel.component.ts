import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { MasterService } from '../../../core/services/master/master.service';
import { TechSkills } from '../../models/interfaces/techSkill.interfaces';
import {
  LangProficiencyModel,
  LanguageModel,
} from '../../models/interfaces/master.interfaces';
import { FilterRequest } from '../../models/interfaces/filterReq.interfaces';
import { FilterService } from 'src/app/core/services/filter/filter.service';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { UserList } from '../../models/interfaces/userList.interfaces';
import { UserService } from '../../service/user/user.service';

@Component({
  selector: 'shared-control-panel',
  templateUrl: './control-panel.component.html',
  styleUrls: ['./control-panel.component.scss'],
})
export class ControlPanelComponent implements OnInit {
  skills: TechSkills[] = [];
  language: LanguageModel[] = [];
  proficiency: LangProficiencyModel[] = [];

  favorites: UserList[] = [];

  selectedTechSks: string[] = [];
  selectedIdLanguage: string = '';
  selectedIdProficiency: string = '';
  term: string = '';
  selectedIdFavorite: string = '';
  public idUser?: number;

  public isRecruiter: boolean = false;

  public filterReq: FilterRequest = {
    habilities: '',
    languageIds: '',
    levelIds: '',
    nameJobTitle: '',
    userListIds: '',
  };

  @Output()
  public onFilterReqVal = new EventEmitter<FilterRequest>();

  constructor(
    private router: Router,
    private masterService: MasterService,
    private filterService: FilterService,
    private authService: AuthService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.checkTechSkills();
    this.checkLanguages();
    this.checkProficiency();
    this.isRecruiter = this.authService.isRecruiter;
    this.idUser = this.authService.idUser;
    this.getFavorites(this.idUser);
  }

  emitFilter() {
    const skillsStr = this.selectedTechSks.join(', ');
    this.filterReq.habilities = skillsStr;
    this.filterReq.languageIds =
      `${this.selectedIdProficiency}` === '' ? '' : '2';
    this.filterReq.levelIds = `${this.selectedIdProficiency}`;
    this.filterReq.nameJobTitle = this.term;
    this.filterReq.userListIds = this.selectedIdFavorite;
    this.onFilterReqVal.emit(this.filterReq);
    this.resetFilterField();
  }

  private resetFilterField() {
    this.selectedIdLanguage = '';
    this.selectedIdProficiency = '';
    this.selectedIdFavorite = '';
  }

  onNewTalent() {
    this.router.navigateByUrl('/home/talent');
  }

  private getTechSkills(): void {
    this.masterService.getTechSkills().subscribe({
      next: (skills) => {
        this.skills = skills;
      },
    });
  }

  private getLanguages(): void {
    this.masterService.getLanguages().subscribe({
      next: (languages) => {
        this.language = languages;
      },
    });
  }

  private getProficiencies(): void {
    this.masterService.getLangProficiency().subscribe({
      next: (proficiency) => {
        this.proficiency = proficiency;
      },
    });
  }

  private getFavorites(userId: number) {
    this.userService.getUserLists(userId).subscribe({
      next: (favorites) => {
        this.favorites = favorites;
      },
    });
  }

  public get isSkillsListEmpty(): boolean {
    return !this.skills || this.skills.length === 0;
  }

  private get isCacheSkillsEmpty(): boolean {
    return (
      !this.masterService.cacheStorage.byTechSkill.techSkills ||
      this.masterService.cacheStorage.byTechSkill.techSkills.length === 0
    );
  }

  private checkTechSkills(): void {
    if (this.isCacheSkillsEmpty) {
      this.getTechSkills();
    } else {
      const cacheTecSkills =
        this.masterService.cacheStorage.byTechSkill.techSkills;
      this.skills = cacheTecSkills;
    }
  }

  private get isCacheLanguagesEmpty(): boolean {
    return (
      !this.masterService.cacheStorage.byLanguage.languages ||
      this.masterService.cacheStorage.byLanguage.languages.length === 0
    );
  }

  private checkLanguages() {
    if (this.isCacheLanguagesEmpty) {
      this.getLanguages();
    } else {
      const cacheLanguages =
        this.masterService.cacheStorage.byLanguage.languages;
      this.language = cacheLanguages;
    }
  }

  private get isCacheProficiencyEmpty(): boolean {
    return (
      !this.masterService.cacheStorage.byLangProficiency.proficiencies ||
      this.masterService.cacheStorage.byLangProficiency.proficiencies.length ===
        0
    );
  }

  private checkProficiency() {
    if (this.isCacheProficiencyEmpty) {
      this.getProficiencies();
    } else {
      const cacheProficiencies =
        this.masterService.cacheStorage.byLangProficiency.proficiencies;
      this.proficiency = cacheProficiencies;
    }
  }

  public get isProficiencyListEmpty(): boolean {
    return !this.proficiency || this.proficiency.length === 0;
  }

  public get isFavoriteListEmpty(): boolean {
    return !this.favorites || this.favorites.length === 0;
  }

  public get totalMsg(): string {
    return this.filterService.resultMsg;
  }
}
