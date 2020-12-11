import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeAnAppDComponent } from './make-an-app-d.component';

describe('MakeAnAppDComponent', () => {
  let component: MakeAnAppDComponent;
  let fixture: ComponentFixture<MakeAnAppDComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MakeAnAppDComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeAnAppDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
