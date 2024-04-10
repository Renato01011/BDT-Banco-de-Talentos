export interface EditDescription {
  description: string;
}

export interface EditAvailability {
  availability: string;
}

export interface EditProfilePicture {
  profilePicture: string;
}

export interface EditSalary {
  idCoin: number,
  initialSalary: number;
  finalSalary: number;
}

export interface EditSocialLinks {
  linkedin: string;
  github: string;
}

export interface EditEducExp {
  institucion: string;
  carrera: string;
  grado: string;
  fechaInicio: string;
  fechaFin: string;
  flActualidad: number;
}

export interface EditWorkExp {
  empresa: string;
  puesto: string;
  fechaInicio: string;
  fechaFin: string;
  flActualidad: number;
}

export interface EditLang {
  idiomaId: number;
  nivelId: number;
  nuEstrellas: number;
}

export interface EditResp {
  id: string;
  message: string;
}
