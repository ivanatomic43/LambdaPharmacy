import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeAnAppPComponent } from './make-an-app-p.component';

describe('MakeAnAppPComponent', () => {
  let component: MakeAnAppPComponent;
  let fixture: ComponentFixture<MakeAnAppPComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MakeAnAppPComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeAnAppPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
