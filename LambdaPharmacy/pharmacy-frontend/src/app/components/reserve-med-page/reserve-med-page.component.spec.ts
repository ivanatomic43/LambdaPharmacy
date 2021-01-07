import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReserveMedPageComponent } from './reserve-med-page.component';

describe('ReserveMedPageComponent', () => {
  let component: ReserveMedPageComponent;
  let fixture: ComponentFixture<ReserveMedPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReserveMedPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReserveMedPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
