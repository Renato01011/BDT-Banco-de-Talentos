import { TestBed } from '@angular/core/testing';

import { DeleteInfoService } from './delete-info.service';

describe('DeleteInfoService', () => {
  let service: DeleteInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeleteInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
