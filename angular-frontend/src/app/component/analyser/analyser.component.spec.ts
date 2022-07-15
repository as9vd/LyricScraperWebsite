import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyserComponent } from './analyser.component';

describe('AnalyserComponent', () => {
  let component: AnalyserComponent;
  let fixture: ComponentFixture<AnalyserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnalyserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnalyserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
