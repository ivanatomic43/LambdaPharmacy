import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllMyMedicinesComponent } from './all-my-medicines.component';

describe('AllMyMedicinesComponent', () => {
  let component: AllMyMedicinesComponent;
  let fixture: ComponentFixture<AllMyMedicinesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllMyMedicinesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllMyMedicinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
