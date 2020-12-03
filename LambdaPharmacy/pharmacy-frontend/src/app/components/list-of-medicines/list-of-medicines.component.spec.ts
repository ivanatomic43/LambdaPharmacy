import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfMedicinesComponent } from './list-of-medicines.component';

describe('ListOfMedicinesComponent', () => {
  let component: ListOfMedicinesComponent;
  let fixture: ComponentFixture<ListOfMedicinesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListOfMedicinesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOfMedicinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
