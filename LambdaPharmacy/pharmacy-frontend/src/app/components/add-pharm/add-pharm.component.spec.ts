import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPharmComponent } from './add-pharm.component';

describe('AddPharmComponent', () => {
  let component: AddPharmComponent;
  let fixture: ComponentFixture<AddPharmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPharmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPharmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
