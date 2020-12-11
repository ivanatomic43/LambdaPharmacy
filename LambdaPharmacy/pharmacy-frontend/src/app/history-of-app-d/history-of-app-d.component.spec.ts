import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryOfAppDComponent } from './history-of-app-d.component';

describe('HistoryOfAppDComponent', () => {
  let component: HistoryOfAppDComponent;
  let fixture: ComponentFixture<HistoryOfAppDComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoryOfAppDComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryOfAppDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
