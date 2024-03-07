export interface AddTechSkill {
  nombre: string;
  anios: string;
}

export interface AddSoftSkill {
  nombre: string;
}

export interface AddWorkExp {
  empresa: string;
  puesto: string;
  fechaInicio: string;
  fechaFin: string;
}

export interface AddEducExp {
  institucion: string;
  carrera: string;
  grado: string;
  fechaInicio: string;
  fechaFin: string;
}

export interface AddLang {
  idiomaId: number;
  nivelId: number;
  nuEstrellas: number;
}

export interface AddResp {
  id: string;
  message: string;
}
