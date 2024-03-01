import { MiscDatum } from './talentResp.interfaces';

export interface CustomTalent {
  idTalent: number;
  name: string;
  surname: string;
  secondSurname: string;
  profilePicture: string;

  initialSalary: number;
  finalSalary: number;
  phone: string;
  linkedin: string;
  github: string;

  avgRating: number;
  miscData: MiscDatum[];
  feedbacks: any[];
}
