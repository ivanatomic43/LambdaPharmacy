import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyPharmacyComponent } from './my-pharmacy.component';

describe('MyPharmacyComponent', () => {
  let component: MyPharmacyComponent;
  let fixture: ComponentFixture<MyPharmacyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyPharmacyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyPharmacyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
