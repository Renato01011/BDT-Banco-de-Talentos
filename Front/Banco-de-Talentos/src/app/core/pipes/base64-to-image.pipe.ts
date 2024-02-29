import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'base64ToImage',
})
export class Base64ToImagePipe implements PipeTransform {
  transform(base64String: string): string {
    if (!base64String || base64String.trim() === '') {
      return './assets/users/no-images.jpeg';
    } else {
      return `data:image/jpeg;base64,${base64String}`;
    }
  }
}
