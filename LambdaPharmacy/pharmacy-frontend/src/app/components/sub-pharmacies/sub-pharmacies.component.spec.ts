import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubPharmaciesComponent } from './sub-pharmacies.component';

describe('SubPharmaciesComponent', () => {
  let component: SubPharmaciesComponent;
  let fixture: ComponentFixture<SubPharmaciesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubPharmaciesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubPharmaciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
