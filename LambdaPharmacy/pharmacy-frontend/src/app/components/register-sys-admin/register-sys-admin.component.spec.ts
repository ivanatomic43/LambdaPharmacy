import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterSysAdminComponent } from './register-sys-admin.component';

describe('RegisterSysAdminComponent', () => {
  let component: RegisterSysAdminComponent;
  let fixture: ComponentFixture<RegisterSysAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterSysAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterSysAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
