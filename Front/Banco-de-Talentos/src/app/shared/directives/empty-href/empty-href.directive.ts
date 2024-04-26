import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: 'a[preventEmptyHref]',
})
export class EmptyHrefDirective {
  constructor(private el: ElementRef) {}

  @HostListener('click', ['$event'])
  onClick(event: Event): void {
    const href = (this.el.nativeElement as HTMLAnchorElement).getAttribute(
      'href'
    );
    if (!href || href.trim() === '') {
      event.preventDefault();
    }
  }
}
