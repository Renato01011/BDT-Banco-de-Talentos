import { Document, MiscDatum, UserListTalent } from './talentResp.interfaces';

export interface CustomTalent {
  idTalent: number;
  name: string;
  surname: string;
  secondSurname: string;
  profilePicture: string;

  initialSalaryRxh: number;
  finalSalaryRxh: number;
  initialSalaryPlanilla: number;
  finalSalaryPlanilla: number;
  phone: string;
  linkedin: string;
  github: string;

  avgRating: number;
  miscData: MiscDatum[];
  feedbacks: any[];

  userListTalent: UserListTalent;

  resume: Document;
}
