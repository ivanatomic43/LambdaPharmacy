import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PenaltyInsightComponent } from './penalty-insight.component';

describe('PenaltyInsightComponent', () => {
  let component: PenaltyInsightComponent;
  let fixture: ComponentFixture<PenaltyInsightComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PenaltyInsightComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PenaltyInsightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
