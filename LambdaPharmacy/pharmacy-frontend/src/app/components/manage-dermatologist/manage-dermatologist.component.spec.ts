import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageDermatologistComponent } from './manage-dermatologist.component';

describe('ManageDermatologistComponent', () => {
  let component: ManageDermatologistComponent;
  let fixture: ComponentFixture<ManageDermatologistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageDermatologistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageDermatologistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
