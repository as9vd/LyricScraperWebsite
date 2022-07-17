import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { debounceTime, map, Observable, startWith } from 'rxjs';
import { Artist } from 'src/app/common/artist/artist';
import { Song } from 'src/app/common/song/song';
import { ArtistService } from 'src/app/service/artist-service/artist.service';
import { SongService } from 'src/app/service/song-service/song.service';

@Component({
  selector: 'song-scraper',
  templateUrl: './song-scraper.component.html',
  styleUrls: ['./song-scraper.component.css']
})
export class SongScraperComponent implements OnInit {
  // public artistList: Artist[] = [];
  public songList: Song[] = [];
  public inputForm!: FormGroup;
  public filteredOptions: Observable<Song[]>;

  constructor(@Inject(ArtistService) private artistService: ArtistService,
              @Inject(SongService) private songService: SongService,
              @Inject(FormBuilder) private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.songService.getSongList().subscribe(
      songs => {
        this.songList = songs;
      }
    )

    this.inputForm = this.formBuilder.group({
        searchBar: new FormControl('')
    });

    this.filteredOptions = this.inputForm.get('searchBar')!.valueChanges.pipe(
      startWith(''),
      map(value => {
        const name = typeof value === 'string' ? value : value?.name;
        return name ? this._filter(name as string) : this.songList.slice();
      }),
    );
  }

  private _filter(name: string): Song[] {
    const filterValue = name.toLowerCase();

    return this.songList.filter(song => song.title.toLowerCase().includes(filterValue));
  }

  onSubmit() {

  }

}