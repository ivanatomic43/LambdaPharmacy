import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDermComponent } from './add-derm.component';

describe('AddDermComponent', () => {
  let component: AddDermComponent;
  let fixture: ComponentFixture<AddDermComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddDermComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDermComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
