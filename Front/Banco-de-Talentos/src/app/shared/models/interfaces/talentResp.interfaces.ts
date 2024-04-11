// Generated by https://quicktype.io

export interface TalentResponse {
  idTalent: number;
  name: string;
  surname: string;
  secondSurname: string;
  profilePicture: string;
  description: string;
  disponibilidad: string;
  montoInicialRxH: number;
  montoFinalRxH: number;
  initialSalary: number;
  finalSalary: number;
  phone: string;
  linkedin: string;
  github: string;
  created: string;
  avgRating: number;
  technicalAbilities: TechnicalAbility[];
  softSkills: SoftSkill[];
  workExperiences: WorkExperience[];
  educationalExperiences: EducationalExperience[];
  documents: Document[];
  miscData: MiscDatum[];
  languageLevels: LanguageLevel[];
  feedbacks: Feedbacks[];
  userListTalent: UserListTalent;
  email: string;
}

export interface Document {
  idDocument: number;
  documentName: string;
  documentType: string;
  document: string;
}

export interface EducationalExperience {
  idEducationalExperience: number;
  institution: string;
  major: string;
  degree: string;
  initialDate: string;
  finalDate: string;
  flActualidad: number;
}

export interface LanguageLevel {
  idTalentLanguage: number;
  idLanguage: number;
  languageName: string;
  languageCode: string;
  idProficiency: number;
  proficiency: string;
  starCount: number;
}

export interface MiscDatum {
  idMasterTalent: number;
  idMaster: number;
  id: number;
  name: string;
  description: string;
  code: string;
  secondCode: string;
}

export interface SoftSkill {
  idSoftSkill: number;
  name: string;
}

export interface TechnicalAbility {
  idTechnicalAbility: number;
  name: string;
  years: number;
}

export interface WorkExperience {
  idWorkExperience: number;
  firm: string;
  jobTitle: string;
  intialDate: string;
  finalDate: string;
  flActualidad: number;
}

export interface Feedbacks {
  idFeedback: number;
  starCount: number;
  description: string;
  idUserFrom: number;
  userFromName: string;
  userFromPhoto: string;
}

export interface UserListTalent {
  idListUser: number;
  listName: string;
  created: Date;
  idListUserTalent: number;
}
