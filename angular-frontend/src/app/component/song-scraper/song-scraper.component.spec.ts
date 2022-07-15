import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SongScraperComponent } from './song-scraper.component';

describe('SongScraperComponent', () => {
  let component: SongScraperComponent;
  let fixture: ComponentFixture<SongScraperComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SongScraperComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SongScraperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
