import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMedComponent } from './add-med.component';

describe('AddMedComponent', () => {
  let component: AddMedComponent;
  let fixture: ComponentFixture<AddMedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddMedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
