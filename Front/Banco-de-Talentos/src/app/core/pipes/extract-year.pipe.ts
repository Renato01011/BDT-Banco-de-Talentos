import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'extractYear',
})
export class ExtractYearPipe implements PipeTransform {
  transform(value: string): string {
    return new Date(parseInt(value)).getFullYear().toString().trim();
  }
}
