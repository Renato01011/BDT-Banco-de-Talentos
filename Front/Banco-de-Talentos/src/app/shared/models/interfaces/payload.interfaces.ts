// Generated by https://quicktype.io

import { Authority } from '../enums/authority.enum';

export interface Payload {
  sub: string;
  iat: number;
  exp: number;
  id: number;
  roles: Role[];
  name: string;
}

export interface Role {
  authority: Authority;
}