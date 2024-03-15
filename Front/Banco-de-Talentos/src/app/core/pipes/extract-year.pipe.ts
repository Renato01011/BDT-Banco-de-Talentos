import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'extractYear',
})
export class ExtractYearPipe implements PipeTransform {
  transform(value: string): string {
    const parts = value.split('-');
    return parts[0].trim();
  }
}
