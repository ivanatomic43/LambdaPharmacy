import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterPharmacyAdminComponent } from './register-pharmacy-admin.component';

describe('RegisterPharmacyAdminComponent', () => {
  let component: RegisterPharmacyAdminComponent;
  let fixture: ComponentFixture<RegisterPharmacyAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterPharmacyAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterPharmacyAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
