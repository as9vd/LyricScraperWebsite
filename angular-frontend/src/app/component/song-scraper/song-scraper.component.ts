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
  public songList: Song[] = [];
  public inputForm!: FormGroup;
  public filteredOptions: Observable<Song[]>;

  public inputLink: string;
  public validLink: boolean = true;

  public jsonButtonPressed: boolean;

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

  // What we want: return a file/path to a file.
  async onClick() {
    this.jsonButtonPressed = true;

    try {
      let path = this.songService.getSongJSON(this.inputLink);

      await path.then(
        value => {return value}
      );

      this.validLink = true;
    } catch (exception) {
      this.validLink = false;
    } finally {
      await console.log("Was that a valid link? " + this.validLink)
    }
  }

  private _filter(name: string): Song[] {
    const filterValue = name.toLowerCase();

    return this.songList.filter(song => song.title.toLowerCase().includes(filterValue));
  }

  displaySong(song: Song): string {
    return song.title;
  }

  onSubmit() {

  }

  testOnClick() {
    this.validLink = !this.validLink;

    console.log(this.validLink);
  }

}