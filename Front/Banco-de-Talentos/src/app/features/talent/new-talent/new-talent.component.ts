import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { switchMap } from 'rxjs';

import { MasterRespConst } from 'src/app/core/global/constants/master-resp.constants';
import * as MasterModels from 'src/app/shared/models/interfaces/master.interfaces';

import { FrmValService } from 'src/app/shared/service/frm-val.service';
import { TalentService } from 'src/app/core/services/talent/talent.service';
import * as TalentModels from 'src/app/shared/models/interfaces/talent.interfaces';
import { LoaderService } from 'src/app/core/services/loader/loader.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { MasterService } from 'src/app/core/services/master/master.service';

const gitHubRegEx = '^https://github.com/[a-zA-Z0-9-]+/?$';
const linkedInRegEx = '^https://www.linkedin.com/in/[a-zA-Z0-9-]+/?$';

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
  countries: MasterModels.CountryModel[] = [];
  cities: MasterModels.CityModel[] = [];

  fileText: string = "Curriculum Vitae";
  fileDetailsText: string = "PDF (max. 800x400px)";
  photoText: string = "Foto de perfil";
  photoDetailsText: string = "PNG o JPG (max. 800x400px)";

  base64file: string = '';
  base64photo: string = '';

  fileUploaded: boolean = false;
  photoUploaded: boolean = false;

  newTalentForm = this.formBuilder.group({
    names: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
    surName: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
    secondSurName: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
    cv: [, [Validators.required]],
    profilePicture: [, [Validators.required]],
    country: [, [Validators.required]],
    city: [, [Validators.required]],
    phone: ['', [Validators.required]],
    description: ['', [Validators.required, Validators.minLength(10), Validators.max(200)]],
    profile: [null, [Validators.required]],
    linkedin: ['', [Validators.required, Validators.pattern(linkedInRegEx)]],
    github: ['', [Validators.required, Validators.pattern(gitHubRegEx)]],
    coin: ['', [Validators.required]],
    initialAmount: [, [Validators.required]],
    finalAmount: [, [Validators.required]],
    technicalAbilities: this.formBuilder.array([
      this.formBuilder.group({
        name: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
        years: [, [Validators.required]]
      })
    ]),
    softSkills: this.formBuilder.array([
      this.formBuilder.group({
        name: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]]
      })
    ]),
    workExperience: this.formBuilder.array([
      this.formBuilder.group({
        firm: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
        job: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
        flagOnFractal: [false],
        initialDate: [, [Validators.required]],
        finalDate: [, [Validators.required]],
        flagCurrently: [false],
      })
    ]),
    educationalExperience: this.formBuilder.array([
      this.formBuilder.group({
        institution: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
        major: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
        degree: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
        flagOnFractal: [false],
        initialDate: [, [Validators.required]],
        finalDate: [, [Validators.required]],
        flagCurrently: [false],
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

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private formValidator: FrmValService,
    private masterService: MasterService,
    private talentService: TalentService,
    private loaderService: LoaderService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.languages = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_LANG) || '{}');
    this.levels = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_PROFICIENCY) || '{}');
    this.profiles = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_PROFILES) || '{}');
    this.coins = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_CURRENCIES) || '{}');
    this.countries = JSON.parse(sessionStorage.getItem(MasterRespConst.STORAGE_CURRENT_COUNTRIES) || '{}');
    this.onCountryChange();
    this.OnFinalSalaryInput();
    this.OnInitialSalaryInput();
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
        firm: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
        job: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
        flagOnFractal: [false],
        initialDate: [, [Validators.required]],
        finalDate: [, [Validators.required]],
        flagCurrently: [false],
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
        institution: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
        major: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
        degree: ['', [Validators.required, Validators.minLength(2), Validators.max(20)]],
        flagOnFractal: [false],
        initialDate: [, [Validators.required]],
        finalDate: [, [Validators.required]],
        flagCurrently: [false],
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

  async onPhotoUpload(event: any) {
    const photo: File = event.target.files[0];
    if (photo) {
      if (photo.type == "image/jpeg" || photo.type == "image/png") {
        const bmp = await createImageBitmap(photo);
        const { width, height } = bmp;
        bmp.close();
        if (width > 800 || height > 400) {
          this.toastService.addProperties('error', "Ocurrio un error", "Las dimensiones de la imagen son demasiado grandes");
          return;
        }
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
        this.toastService.addProperties('error', "Ocurrio un error", "Imagen no es PNG o JPG");
        return;
      }
    }
    else {
      this.photoText = "Error al Subir";
      this.toastService.addProperties('error', "Ocurrio un error", "Error al subir imagen, vuelva a intentar");
    }
  }

  onFileUpload(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      if (file.size > 50000000) {
        this.toastService.addProperties('error', "Ocurrio un error", "Documento es demasiado pesado");
        return;
      }
      if (file.type != "application/pdf") {
        this.toastService.addProperties('error', "Ocurrio un error", "Documento no es PDF");
        return;
      }
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
      //this.fileText = "Error al Subir";
      this.toastService.addProperties('error', "Ocurrio un error", "Error al subir PDF, vuelva a intentar");
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

  onCountryChange() {
    this.newTalentForm.get("country")!.valueChanges.pipe(
      switchMap((country) => this.masterService.getCities(country.id))
    ).subscribe((cities) => this.cities = cities);
  }

  OnFractalEducationCheckboxChange(abstractControl: AbstractControl) {
    let formGroup = abstractControl as FormGroup;
    if (formGroup.get("flagOnFractal")?.value) {
      formGroup.controls["institution"].disable();
      formGroup.controls["institution"].setValue('Fractal');
    }
    else {
      formGroup.controls["institution"].enable();
      formGroup.controls["institution"].setValue('');
    }
  }

  OnFractalWorkCheckboxChange(abstractControl: AbstractControl) {
    let formGroup = abstractControl as FormGroup;
    if (formGroup.get("flagOnFractal")?.value) {
      formGroup.controls["firm"].disable();
      formGroup.controls["firm"].setValue('Fractal');
    }
    else {
      formGroup.controls["firm"].enable();
      formGroup.controls["firm"].setValue('');
    }
  }

  OnCurrentlyCheckboxChange(abstractControl: AbstractControl) {
    let formGroup = abstractControl as FormGroup;
    if (formGroup.get("flagCurrently")?.value) {
      formGroup.controls["finalDate"].disable();
      formGroup.controls["finalDate"].setValue(new Date());
    }
    else {
      formGroup.controls["finalDate"].enable();
      formGroup.controls["finalDate"].setValue(null);
    }
  }

  OnCountryChange() {
    this.newTalentForm.controls["city"].setValue(null);
  }

  OnLanguageChange(abstractControl: AbstractControl) {
    let formGroup = abstractControl as FormGroup;
    formGroup.controls['level'].setValue(null);
    formGroup.controls['starCount'].setValue(null);
  }

  OnInitialSalaryInput() {
    this.newTalentForm.get("initialAmount")!.valueChanges.subscribe((value: number) => {
      if (this.newTalentForm.get("initialAmount")?.value == null) { return; }
      if (this.newTalentForm.get('finalAmount')?.value != null && value > this.newTalentForm.get('finalAmount')?.value) {
        this.newTalentForm.controls['initialAmount'].setValue(null);
        this.toastService.addProperties('error', "Ocurrio un error", "Cantidad incial no puede ser mayor que cantidad final");
      }
    });
  }

  OnFinalSalaryInput() {
    this.newTalentForm.get("finalAmount")!.valueChanges.subscribe((value: number) => {
      if (this.newTalentForm.get("finalAmount")?.value == null) { return; }
      if (this.newTalentForm.get('initialAmount')?.value != null && this.newTalentForm.get('initialAmount')?.value > value) {
        this.newTalentForm.controls['finalAmount'].setValue(null);
        this.toastService.addProperties('error', "Ocurrio un error", "Cantidad final no puede ser menor que cantidad inicial");
      }
    });
  }

  OnInitialDateSelect(abstractControl: AbstractControl) {
    let formGroup = abstractControl as FormGroup;
    if (formGroup.get('finalDate')?.value != null && formGroup.get('finalDate')?.value < formGroup.get('initialDate')?.value) {
      formGroup.controls['initialDate'].setValue(null);
      this.toastService.addProperties('error', "Ocurrio un error", "Fecha inicial no puede ser mayor a fecha final");
    }
  }

  OnFinalDateSelect(abstractControl: AbstractControl) {
    let formGroup = abstractControl as FormGroup;
    if (formGroup.get('initialDate')?.value != null && formGroup.get('finalDate')?.value < formGroup.get('initialDate')?.value) {
      formGroup.controls['finalDate'].setValue(null);
      this.toastService.addProperties('error', "Ocurrio un error", "Fecha final no puede ser menor a fecha inicial");
    }
  }

  AddTalent() {
    //console.log(this.newTalentForm.value);
    if (this.newTalentForm.valid) {
      this.loaderService.showLoader();
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
        idPais: this.newTalentForm.get('country')?.value.id,
        idCiudad: this.newTalentForm.get('city')?.value.id,
        linkedin: this.newTalentForm.get('linkedin')?.value,
        github: this.newTalentForm.get('github')?.value,
        tipoMoneda: this.newTalentForm.get('coin')?.value.id,
        montoInicial: this.newTalentForm.get('initialAmount')?.value,
        montoFinal: this.newTalentForm.get('finalAmount')?.value,
        celular: this.newTalentForm.get("phone")?.value,
        habilidadesTecnicas: this.getTechnicalAbilitiesArrayValues(),
        habilidadesBlandas: this.getSoftSkillsArrayValues(),
        experienciasLaborales: this.getWorkExperienceArrayValues(),
        experienciasEducativas: this.getEducationalExperienceArrayValues(),
        idiomas: this.getLanguagesArrayValues(),
      };
      this.talentService.postNewTalent(newtalent).subscribe(
        (response) => {
          console.log(response);
          this.loaderService.hideLoader();
          this.toastService.addProperties('success', "Éxito", response.message);
          this.router.navigateByUrl('/home/dashboard/list');
          
        },
        (error) => {
          this.loaderService.hideLoader();
          this.toastService.addProperties('error', "Ocurrio un error", error.message);
        });
    }
    else {
      this.toastService.addProperties('error', "Ocurrió un problema", "Revise los campos ingresados");
      this.ValidateAllFormFields(this.newTalentForm);
    }
  }

}
