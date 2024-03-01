import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'extractFullName',
})
export class ExtractFullNamePipe implements PipeTransform {
  transform(value: string): string {
    const index = value.indexOf('|');
    if (index !== -1) {
      return value.substring(0, index).trim();
    } else {
      return value;
    }
  }
}
