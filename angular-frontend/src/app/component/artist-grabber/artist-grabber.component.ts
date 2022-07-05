import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Artist } from 'src/app/common/artist/artist';
import { ArtistService } from 'src/app/service/artist-service/artist.service';

@Component({
  selector: 'app-artist-grabber',
  templateUrl: './artist-grabber.component.html',
  styleUrls: ['./artist-grabber.component.css']
})
export class ArtistGrabberComponent implements OnInit {
  artists!: Artist[];

  constructor(@Inject(ArtistService) private artistService: ArtistService,
              @Inject(ActivatedRoute) private route: ActivatedRoute) { }

  ngOnInit() {
    this.listArtists();
  }

  listArtists() {
    this.artistService.getArtistList().subscribe(
      data => {
        this.artists = data;
        console.log(data);
      }
    )
  }

}