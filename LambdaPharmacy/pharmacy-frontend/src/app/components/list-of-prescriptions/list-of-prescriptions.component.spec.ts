import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfPrescriptionsComponent } from './list-of-prescriptions.component';

describe('ListOfPrescriptionsComponent', () => {
  let component: ListOfPrescriptionsComponent;
  let fixture: ComponentFixture<ListOfPrescriptionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListOfPrescriptionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOfPrescriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
