import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'extractJobPosition',
})
export class ExtractJobPositionPipe implements PipeTransform {
  transform(value: string): string {
    const index = value.indexOf('|');
    if (index !== -1) {
      return value.substring(index + 1).trim();
    } else {
      return '';
    }
  }
}
