import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistsComponent } from './dermatologists.component';

describe('DermatologistsComponent', () => {
  let component: DermatologistsComponent;
  let fixture: ComponentFixture<DermatologistsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DermatologistsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
