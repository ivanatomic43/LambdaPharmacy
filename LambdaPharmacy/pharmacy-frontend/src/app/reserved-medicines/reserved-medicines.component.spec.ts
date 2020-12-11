import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservedMedicinesComponent } from './reserved-medicines.component';

describe('ReservedMedicinesComponent', () => {
  let component: ReservedMedicinesComponent;
  let fixture: ComponentFixture<ReservedMedicinesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReservedMedicinesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservedMedicinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
