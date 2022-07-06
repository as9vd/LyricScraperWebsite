import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistGrabberComponent } from './artist-grabber.component';

describe('ArtistGrabberComponent', () => {
  let component: ArtistGrabberComponent;
  let fixture: ComponentFixture<ArtistGrabberComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArtistGrabberComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArtistGrabberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
