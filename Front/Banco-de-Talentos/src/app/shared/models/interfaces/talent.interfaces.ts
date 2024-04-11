export interface NewTalentModel {
  nombre: string;
  apellidoPaterno: string;
  apellidoMaterno: string;
  fotoDePerfil: string;
  documentos: [
    {
      nombre: string;
      tipoArchivo: string;
      archivo: string;
    }
  ],
  descripcion: string,
  email: string;
  disponibilidad: string,
  idPuestoActual: number,
  idPais: number,
  idCiudad: number,
  linkedin: string,
  github: string,
  idTipoMoneda: number,
  montoInicialRxH: number,
  montoFinalRxH: number,
  montoInicialPlanilla: number,
  montoFinalPlanilla: number,
  celular: string,
  habilidadesTecnicas: TechnicalAbilitiesTalentModel[],
  habilidadesBlandas: SoftSkillsTalentModel[],
  experienciasLaborales: WorkExperienceTalentModel[],
  experienciasEducativas: EducationalExperienceTalentModel[],
  idiomas: LanguagesTalentModel[],
}

export interface TechnicalAbilitiesTalentModel {
  nombre: string;
  anios: number;
}

export interface SoftSkillsTalentModel {
  nombre: string;
}

export interface WorkExperienceTalentModel {
  empresa: string;
  puesto: string;
  fechaInicio: Date;
  fechaFin: Date;
  flActualidad: number;
  functions: string;
}

export interface EducationalExperienceTalentModel {
  institucion: string;
  carrera: string;
  grado: string;
  fechaInicio: Date;
  fechaFin: Date;
  flActualidad: number;
}

export interface LanguagesTalentModel {
  idiomaId: number;
  nivelId: number;
  nuEstrellas: number;
}
