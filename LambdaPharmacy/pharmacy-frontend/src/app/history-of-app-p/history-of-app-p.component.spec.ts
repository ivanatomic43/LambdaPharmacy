import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryOfAppPComponent } from './history-of-app-p.component';

describe('HistoryOfAppPComponent', () => {
  let component: HistoryOfAppPComponent;
  let fixture: ComponentFixture<HistoryOfAppPComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoryOfAppPComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryOfAppPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
