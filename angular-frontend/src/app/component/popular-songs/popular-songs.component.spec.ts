import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopularSongsComponent } from './popular-songs.component';

describe('PopularSongsComponent', () => {
  let component: PopularSongsComponent;
  let fixture: ComponentFixture<PopularSongsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopularSongsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopularSongsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
