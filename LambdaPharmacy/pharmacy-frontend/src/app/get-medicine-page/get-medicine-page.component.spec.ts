import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetMedicinePageComponent } from './get-medicine-page.component';

describe('GetMedicinePageComponent', () => {
  let component: GetMedicinePageComponent;
  let fixture: ComponentFixture<GetMedicinePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetMedicinePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetMedicinePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
