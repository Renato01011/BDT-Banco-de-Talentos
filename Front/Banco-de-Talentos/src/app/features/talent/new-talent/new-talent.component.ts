import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

import { MasterRespConst } from 'src/app/core/global/constants/master-resp.constants';
import * as MasterModels from 'src/app/shared/models/interfaces/master.interfaces';

import { FrmValService } from 'src/app/shared/service/frm-val.service';
import { TalentService } from 'src/app/core/services/talent/talent.service';
import * as TalentModels from 'src/app/shared/models/interfaces/talent.interfaces';

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

  fileText: string = "Curriculum Vitae";
  fileDetailsText: string = "PDF (max. 800x400px)";
  photoText: string = "Foto de perfil";
  photoDetailsText: string = "PNG o JPG (max. 800x400px)";

  base64file: string = '';
  base64photo: string = '';

  fileUploaded: boolean = false;
  photoUploaded: boolean = false;

  newTalentForm = this.formBuilder.group({
    names: ['', [Validators.required]],
    surName: ['', [Validators.required]],
    secondSurName: ['', [Validators.required]],
    cv: [, [Validators.required]],
    profilePicture: [, [Validators.required]],
    description: ['', [Validators.required]],
    profile: [null, [Validators.required, Validators.nullValidator]],
    linkedin: ['', [Validators.required]],
    github: ['', [Validators.required]],
    coin: ['', [Validators.required]],
    initialAmount: [, [Validators.required]],
    finalAmount: [, [Validators.required]],
    technicalAbilities: this.formBuilder.array([
      this.formBuilder.group({
        name: ['', [Validators.required]],
        years: [, [Validators.required]]
      })
    ]),
    softSkills: this.formBuilder.array([
      this.formBuilder.group({
        name: ['', [Validators.required]]
      })
    ]),
    workExperience: this.formBuilder.array([
      this.formBuilder.group({
        firm: ['', [Validators.required]],
        job: ['', [Validators.required]],
        initialDate: [, [Validators.required]],
        finalDate: [, [Validators.required]],
      })
    ]),
    educationalExperience: this.formBuilder.array([
      this.formBuilder.group({
        institution: ['', [Validators.required]],
        major: ['', [Validators.required]],
        degree: ['', [Validators.required]],
        initialDate: [, [Validators.required]],
        finalDate: [, [Validators.required]],
      })
    ]),
    knownLanguages: this.formBuilder.array([
      this.formBuilder.group({
        language: [, [Validators.required]],
        level: [, [Validators.required]],
        starCount: [, [Validators.required]],
      })
    ]),
  });

  constructor(private router: Router, private formBuilder: FormBuilder, private formValidator: FrmValService, private talentService: TalentService) {}

  ngOnInit(): void {
    this.languages = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_LANG) || '{}');
    this.levels = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_PROFICIENCY) || '{}');
    this.profiles = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_PROFILES) || '{}');
    this.coins = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_CURRENCIES) || '{}');
  }

  ngOnDestroy(): void { }

  AddTechnicalAbility() {
    (this.newTalentForm.get('technicalAbilities') as FormArray).push(
      this.formBuilder.group({
        name: ['', [Validators.required]],
        years: [, [Validators.required]]
      })
    );
  }
  DeleteTechnicalAbility(index: number) {
    (this.newTalentForm.get('technicalAbilities') as FormArray).removeAt(index);
  }
  getTechnicalAbilities(): AbstractControl[] {
    return (<FormArray> this.newTalentForm.get('technicalAbilities')).controls;
  }

  AddSoftSkill() {
    (this.newTalentForm.get('softSkills') as FormArray).push(
      this.formBuilder.group({
        name: ['', [Validators.required]]
      })
    );
  }
  DeleteSoftSkill(index: number) {
    (this.newTalentForm.get('softSkills') as FormArray).removeAt(index);
  }
  getSoftSkills(): AbstractControl[] {
    return (<FormArray> this.newTalentForm.get('softSkills')).controls;
  }

  AddWorkExperience() {
    (this.newTalentForm.get('workExperience') as FormArray).push(
      this.formBuilder.group({
        firm: ['', [Validators.required]],
        job: ['', [Validators.required]],
        initialDate: [, [Validators.required]],
        finalDate: [, [Validators.required]],
      })
    );
  }
  DeleteWorkExperience(index: number) {
    (this.newTalentForm.get('workExperience') as FormArray).removeAt(index);
  }
  getWorkExperience(): AbstractControl[] {
    return (<FormArray> this.newTalentForm.get('workExperience')).controls;
  }

  AddEducationalExperience() {
    (this.newTalentForm.get('educationalExperience') as FormArray).push(
      this.formBuilder.group({
        institution: ['', [Validators.required]],
        major: ['', [Validators.required]],
        degree: ['', [Validators.required]],
        initialDate: [, [Validators.required]],
        finalDate: [, [Validators.required]],
      })
    );
  }
  DeleteEducationalExperience(index: number) {
    (this.newTalentForm.get('educationalExperience') as FormArray).removeAt(index);
  }
  getEducationalExperience(): AbstractControl[] {
    return (<FormArray> this.newTalentForm.get('educationalExperience')).controls;
  }

  AddKnownLanguage() {
    (this.newTalentForm.get('knownLanguages') as FormArray).push(
      this.formBuilder.group({
        language: [, [Validators.required]],
        level: [, [Validators.required]],
        starCount: ['', [Validators.required]],
      })
    );
  }
  DeleteKnownLanguage(index: number) {
    (this.newTalentForm.get('knownLanguages') as FormArray).removeAt(index);
  }
  getKnownLanguages(): AbstractControl[] {
    return (<FormArray> this.newTalentForm.get('knownLanguages')).controls;
  }

  isValidField(field: string) {
    return this.formValidator.isValidField(this.newTalentForm, field);
  }

  onVolver() {
    this.router.navigateByUrl('/home/dashboard/list');
  }

  onPhotoUpload(event: any) {
    const photo: File = event.target.files[0];
    if (photo) {
      this.photoUploaded = true;
      //this.photo = photo;
      const photoReader = new FileReader();
      photoReader.readAsDataURL(photo);
      photoReader.onloadend = () => {
        this.base64photo = photoReader.result as string;
      }
      this.photoText = "Archivo Correctamente Subido";
      this.photoDetailsText = photo.name;
    }
    else {
      this.photoText = "Error al Subir";
    }
  }

  onFileUpload(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      this.fileUploaded = true;
      //this.file = file;
      const fileReader = new FileReader();
      fileReader.readAsDataURL(file);
      fileReader.onloadend = () => {
        this.base64file = fileReader.result as string;
      }
      this.fileText = "Archivo Correctamente Subido";
      this.fileDetailsText = file.name;
    }
    else {
      this.fileText = "Error al Subir";
    }
  }

  ValidateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({ onlySelf: true });
      } 
      else if (control instanceof FormGroup) {
        this.ValidateAllFormFields(control);
      }
      else if (control instanceof FormArray) {
        for (let group of control.controls) {
          this.ValidateAllFormFields(group as FormGroup);
        }
      }
    });
  }

  getTechnicalAbilitiesArrayValues() {
    let objectArray: TalentModels.TechnicalAbilitiesTalentModel[] = [];
    this.getTechnicalAbilities().forEach(formGroup => {
      objectArray.push({
        nombre: formGroup.get('name')?.value,
        anios: formGroup.get('years')?.value,
      });
    });
    return objectArray;
  }

  getSoftSkillsArrayValues() {
    let objectArray: TalentModels.SoftSkillsTalentModel[] = [];
    this.getSoftSkills().forEach(formGroup => {
      objectArray.push({
        nombre: formGroup.get('name')?.value,
      });
    });
    return objectArray;
  }

  getWorkExperienceArrayValues() {
    let objectArray: TalentModels.WorkExperienceTalentModel[] = [];
    this.getWorkExperience().forEach(formGroup => {
      objectArray.push({
        empresa: formGroup.get('firm')?.value,
        puesto: formGroup.get('job')?.value,
        fechaInicio: formGroup.get('initialDate')?.value,
        fechaFin: formGroup.get('finalDate')?.value,
      });
    });
    return objectArray;
  }

  getEducationalExperienceArrayValues() {
    let objectArray: TalentModels.EducationalExperienceTalentModel[] = [];
    this.getEducationalExperience().forEach(formGroup => {
      objectArray.push({
        institucion: formGroup.get('institution')?.value,
        carrera: formGroup.get('major')?.value,
        grado: formGroup.get('degree')?.value,
        fechaInicio: formGroup.get('initialDate')?.value,
        fechaFin: formGroup.get('finalDate')?.value,
      });
    });
    return objectArray;
  }

  getLanguagesArrayValues() {
    let objectArray: TalentModels.LanguagesTalentModel[] = [];
    this.getKnownLanguages().forEach(formGroup => {
      objectArray.push({
        idiomaId: formGroup.get('language')?.value.id,
        nivelId: formGroup.get('level')?.value.id,
        nuEstrellas: formGroup.get('starCount')?.value,
      });
    });
    return objectArray;
  }

  AddTalent() {
    console.log(this.newTalentForm.value);
    if (this.newTalentForm.valid) {
      let newtalent: TalentModels.NewTalentModel = {
        nombre: this.newTalentForm.get('names')?.value,
        apellidoPaterno: this.newTalentForm.get('surName')?.value,
        apellidoMaterno: this.newTalentForm.get('secondSurName')?.value,
        fotoDePerfil: this.base64photo.split(',')[1],
        documentos: [
          {
            nombre: this.fileDetailsText,
            tipoArchivo: "PDF",
            archivo: this.base64file.split(',')[1],
          }
        ],
        descripcion: this.newTalentForm.get('description')?.value,
        idPuestoActual: this.newTalentForm.get('profile')?.value.id,
        idPais: 1,
        idCiudad: 1,
        linkedin: this.newTalentForm.get('linkedin')?.value,
        github: this.newTalentForm.get('github')?.value,
        tipoMoneda: this.newTalentForm.get('coin')?.value.id,
        montoInicial: this.newTalentForm.get('initialAmount')?.value,
        montoFinal: this.newTalentForm.get('finalAmount')?.value,
        celular: "999999999",
        habilidadesTecnicas: this.getTechnicalAbilitiesArrayValues(),
        habilidadesBlandas: this.getSoftSkillsArrayValues(),
        experienciasLaborales: this.getWorkExperienceArrayValues(),
        experienciasEducativas: this.getEducationalExperienceArrayValues(),
        idiomas: this.getLanguagesArrayValues(),
      };
      this.talentService.postNewTalent(newtalent).subscribe(response => console.log(response)); 
    }
    else {
      this.ValidateAllFormFields(this.newTalentForm);
    }
  }

}
